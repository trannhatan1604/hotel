package com.hotel.controller.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hotel.constant.SystemConstant;
import com.hotel.converter.PromotionConverter;
import com.hotel.converter.TypeRoomConverter;
import com.hotel.dto.FeedBackDTO;
import com.hotel.dto.MyUser;
import com.hotel.dto.OrderDTO;
import com.hotel.dto.PromotionDTO;
import com.hotel.dto.RoomDTO;
import com.hotel.dto.TypeRoomDTO;
import com.hotel.entity.PromotionEntity;
import com.hotel.entity.TypeRoomEntity;
import com.hotel.service.IFeedBackService;
import com.hotel.service.IOrderService;
import com.hotel.service.IPromotionService;
import com.hotel.service.IRoomService;
import com.hotel.service.impl.TypeRoomService;
import com.hotel.util.CompareDate;
import com.hotel.util.PriceFormat;
import com.hotel.util.SecurityUtils;

@Controller(value ="DetailsRoomOfWebController")
public class DetailsRoomController {
    @Autowired
    private TypeRoomService TypeRoomService;
    
    @Autowired
    private IRoomService roomService;
    
    @Autowired
	private PriceFormat priceFormat;
    
    @Autowired
    private IFeedBackService feedBackService;
    
    @Autowired
    private IOrderService orderService;
    
    @Autowired
    private IPromotionService promotionService;
    
    @Autowired
    private CompareDate compareDate;
  

    @RequestMapping(value = "/chi-tiet", method = RequestMethod.GET)
    public ModelAndView roomdetails(@RequestParam("id") int roomId,@RequestParam(value = "dateRange", required = false) String dateRange,
    		@RequestParam(value = "promotionId", required = false) Integer promotionId) {
        ModelAndView mav = new ModelAndView();
        TypeRoomEntity room = TypeRoomService.findById(roomId);
        //đổi qua dto để format giá tiền
        TypeRoomDTO dto = new TypeRoomDTO();
		dto = TypeRoomConverter.toDTO(room);
		dto.setPriceFormat(priceFormat.format(dto.getPrice()));
		//set ngày 
		LocalDate today = LocalDate.now();
		
		LocalDate lastMonth = today.minusMonths(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		//kiểm tra đã đăng nhập chưa
		MyUser user = SecurityUtils.getPrincipal();
		mav.addObject("user", user);
		
		int countRoomFree = roomService.CountRoomFree(roomId);
        mav.addObject("typeroom", dto);
        mav.addObject("countRoomFree", countRoomFree);
        
		//set dateRange chạy lần đầu chưa đặt
		if(dateRange==null) {
			dateRange = lastMonth.format(formatter) + " - " + today.format(formatter);
			
		}
		else {
			mav.addObject("dateRange", dateRange);
			
			//Xử lý xem còn phòng không
			RoomDTO roomDto = new RoomDTO();
			roomDto.setDateRange(dateRange);
			
			//Ngày hiện tại
			System.out.println("check date late now: "+compareDate.CompareDate(roomDto.getCheckinDate().toLocalDate()));
			try {
				if(!compareDate.CompareDate(roomDto.getCheckinDate().toLocalDate())) {
					mav.addObject("lateDateNow", "1");
					System.out.println("lateDateNow");
				}
				else {
					if(roomDto.getCheckinDate().equals(roomDto.getCheckoutDate()))
					{
						mav.addObject("errorDate", "1");
						System.out.println("errorDate");
					}
					else {
						System.out.println("Đếm"+roomService.countAvailableRoomWebs(roomDto.getCheckinDate(), roomDto.getCheckoutDate(), roomId));
						if(roomService.countAvailableRoomWebs(roomDto.getCheckinDate(), roomDto.getCheckoutDate(), roomId)<1)
						{
							
							mav.addObject("notAvailable", "1");
							System.out.println("notAvailable");
						}
						else {

							float price = 0;
							int idCustomer = SecurityUtils.getPrincipal().getId();
							//kiểm tra xem có app khuyến mãi không
							if(promotionService.findById(promotionId)==null) {
								price = room.getPrice();
							}
							else {

								int promotion = promotionService.findById(promotionId).getLevel();
								price = room.getPrice() - (room.getPrice()*promotion)/100;
								
							}
							//* số ngày đặt
							long millisecondsPerDay = 1000 * 60 * 60 * 24;
							long daysBetween = (roomDto.getCheckoutDate().getTime() - roomDto.getCheckinDate().getTime()) / millisecondsPerDay;
							price*=daysBetween;
							
							RoomDTO roomOrder = roomService.findOneAvailableRoom(roomDto.getCheckinDate(), roomDto.getCheckoutDate(), roomId);
							//Đưa dữ liệu vào để đặt phòng
							OrderDTO order = new OrderDTO(roomDto.getCheckinDate(), roomDto.getCheckoutDate(), room.getQuantity(), 
															price, idCustomer, 0, SystemConstant.PENDING, roomOrder.getId(), promotionId);
							orderService.addOrderWeb(order);
							mav.setViewName("redirect:/trang-chu?success=1");
					        return mav;

						}
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		
		//Phân trang feedback
		FeedBackDTO model = new FeedBackDTO();
		model.setPage(1);
		model.setSearchValue("");
		model.setLimit(3);
		mav.addObject("model", model);
		
        dto.setDateRange(dateRange);
		mav.setViewName("web/details");
        return mav;

    }
    
    
    @RequestMapping(value="/feedback", method = RequestMethod.GET)
    public ModelAndView feedback(@RequestParam(name="roomId", required = false) Integer roomId, @RequestParam(name="id", required = false) Integer orderId) {
    	ModelAndView mav = new ModelAndView("web/feedback");
    	FeedBackDTO dto = new FeedBackDTO();
    	
    	dto.setAccountId(SecurityUtils.getPrincipal().getId());
    	dto.setRoomId(roomId);
    	dto.setOrderId(orderId);
    	mav.addObject("model", dto);
    	System.out.println(dto.getAccountId()+" "+dto.getRoomId());
    	return mav;
    }
    
    @RequestMapping(value="/phan-hoi", method = RequestMethod.GET)
    public ModelAndView feedbacks(@ModelAttribute("model") @Valid FeedBackDTO dto, BindingResult result) {
    	ModelAndView mav = new ModelAndView();
    	if(result.hasErrors()) {
    		mav.setViewName("web/feedback");
			mav.addObject("model", dto);
			return mav;
    	}
    	else {
	    	dto.setId(null);
	    	dto.setAccountId(SecurityUtils.getPrincipal().getId());
	    	dto.setStatus(SystemConstant.FEEDBACKED);
	    	feedBackService.update(dto);
	    	orderService.updateStatus(dto.getOrderId(), SystemConstant.FEEDBACKED);
	    	mav.setViewName("redirect:/lich-su?success=1");
	    	return mav;
    	}
    }
    
    @RequestMapping(value="/huy-phong", method = RequestMethod.GET)
    public ModelAndView refuse(@RequestParam(name = "ma", required = false) Integer maOrder) {
    	System.out.println(maOrder);
    	orderService.updateStatus(maOrder, SystemConstant.CANCEL);
    	ModelAndView mav = new ModelAndView("redirect:/lich-su?refuse=1");
    	return mav;
    }
    
    @ModelAttribute("typerooms")
	 public List<TypeRoomDTO> getAllType() {
    	List<TypeRoomEntity> entities = TypeRoomService.findAllRoom();
    	List<TypeRoomDTO> dtos = new ArrayList<TypeRoomDTO>();
    	for (TypeRoomEntity entity : entities) {
    		TypeRoomDTO dto = new TypeRoomDTO();
			dto = TypeRoomConverter.toDTO(entity);
			dto.setPriceFormat(priceFormat.format(dto.getPrice()));
			dtos.add(dto);
		}
      return dtos;
  }
    @ModelAttribute("promotions")
	 public List<PromotionDTO> getAllPromotion() {
   	List<PromotionEntity> entities = promotionService.getPromotionAvailable();
   	List<PromotionDTO> dtos = new ArrayList<PromotionDTO>();
   	for (PromotionEntity entity : entities) {
   		PromotionDTO dto = new PromotionDTO();
			dto = PromotionConverter.toDTO(entity);
			dtos.add(dto);
		}
     return dtos;
 }
//    @ModelAttribute("feedbacks")
//	 public List<FeedBackDTO> getAllFeedBack() {
//  	List<FeedBackDTO> dtos = feedBackService.findAll();
//  	for(FeedBackDTO dto : dtos) {
//  		//TÌm vị trí ' ' đầu tiên
//  		int indexSpace = dto.getAccount().getFullName().indexOf(' ');
//  		//tách lấy họ
//  		String lastName = dto.getAccount().getFullName().substring(0, indexSpace);
//  		dto.setAccountName(lastName);
//  		TypeRoomEntity typeroom = TypeRoomService.findById(dto.getRoom().getTyperoom().getId());
//  		dto.setTypeRoomName(typeroom.getName());
//  		System.out.println("Adu sao lại vô đây"+dto.getAccountName());
//  	}
//    return dtos;
//}
    @RequestMapping(value = "/chi-tiet/phan-hoi" , method = RequestMethod.POST)
    public ModelAndView getFeedBack(@ModelAttribute("model") FeedBackDTO dto) {
    	System.out.println("hehehehe ");
    	Pageable pageable = new PageRequest(dto.getPage() - 1, dto.getLimit());
    	List<FeedBackDTO> dtos = feedBackService.findFeedBack(SystemConstant.HABNDL, pageable);
    	System.out.println(dtos.size());
      	for(FeedBackDTO feedback : dtos) {
      		System.out.println("hehehehe "+feedback.getAccountName());
      		//TÌm vị trí ' ' đầu tiên
      		int indexSpace = feedback.getAccountName().indexOf(' ');
      		//tách lấy họ
      		String lastName = feedback.getAccountName().substring(0, indexSpace);
      		feedback.setAccountName(lastName);
      		TypeRoomEntity typeroom = TypeRoomService.findById(feedback.getRoom().getTyperoom().getId());
      		feedback.setTypeRoomName(typeroom.getName());
      		System.out.println("Có vô đây không vậy "+feedback.getAccountName());
      	}
      	dto.setTotalItem(feedBackService.getTotalItem(SystemConstant.HABNDL));
      	dto.setTotalPage((int) Math.ceil((double) dto.getTotalItem() / dto.getLimit()));
      	ModelAndView mav = new ModelAndView("web/pagingFeedBack");
      	dto.setListResult(dtos);
        return mav;
    }
}