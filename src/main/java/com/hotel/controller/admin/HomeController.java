package com.hotel.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hotel.dto.HomeDTO;
import com.hotel.service.IHomeService;
import com.hotel.service.IPromotionService;
import com.hotel.service.IRoomService;

@Controller(value = "HomeControllerOfAdmin")
public class HomeController {
	@Autowired
	private IHomeService homeService;
	@Autowired
	private IRoomService roomService;

	@RequestMapping(value = "/quan-tri/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage() {
		HomeDTO homeDTO = new HomeDTO();
		homeDTO.setEmptyRoom(homeService.getEmptyRoom());
		homeDTO.setTotalRoom(roomService.getTotalItem());
		homeDTO.setAvailablePromotion(homeService.getAvailablePromotion());
		homeDTO.setRevenueToday(homeService.getRevenueToDay());
		homeDTO.setRevenueThisMonth(homeService.getRevenueThisMonth()); 
		homeDTO.setMostOrderTypeRoom(homeService.getMostOrderTypeRoom());
		homeDTO.setPercentRoomFree(homeService.getPercentRoomFree());
		
		homeDTO.setListTypeRoomPercent(homeService.getTypeRoomPercent());
		homeDTO.setCountOrderToday(homeService.getCountOrderToday());
		homeDTO.setChartTypeRoomDataList(homeService.getCanvasjsChartData());
		homeDTO.setChartRevenueMonthsDataList(homeService.getRevenueMonthsOfYear());
		homeDTO.setChartRevenueQuarterDataList(homeService.getQuarterRevenue());
		homeDTO.setChartRevenueRecentDaysDataList(homeService.getRevenueRecentDays(homeDTO.getDays()));
		
		homeDTO.setNewOrderList(homeService.getNewOrderList());
		homeDTO.setOrderTodayCheckoutList(homeService.getOrderTodayCheckout());
		homeDTO.setCountCheckoutOrderToday(homeService.getOrderTodayCheckout().size());

		ModelAndView mav = new ModelAndView("admin/home");
		mav.addObject("model", homeDTO);
		return mav;
	}
	
	@RequestMapping(value = "/quan-tri/trang-chu/search", method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute("model") HomeDTO homeDTO) {
		homeDTO.setChartRevenueRecentDaysDataList(homeService.getRevenueRecentDays(homeDTO.getDays()));
		ModelAndView mav = new ModelAndView("admin/search");
		mav.addObject("model", homeDTO);
		return mav;
	}
}
