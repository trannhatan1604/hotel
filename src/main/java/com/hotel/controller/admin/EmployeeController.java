package com.hotel.controller.admin;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hotel.constant.SystemConstant;
import com.hotel.converter.AccountConverter;
import com.hotel.dto.AccountDTO;
import com.hotel.dto.FeedBackDTO;
import com.hotel.entity.AccountEntity;
import com.hotel.entity.RoleEntity;
import com.hotel.service.IFeedBackService;
import com.hotel.service.impl.AccountService;
import com.hotel.util.MessageUtil;
import com.hotel.util.SaveFile;
import com.hotel.util.SecurityUtils;

@Controller
public class EmployeeController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private IFeedBackService feedBackService;
	
	@Autowired
	private SaveFile multipartFile;
	
	@Autowired
	private SecurityUtils securityUtils;
	
	@Autowired
	private MessageUtil messageUtil;
	
	private static String nophoto = "/template/admin/img/account/notphoto.png";
	
	@RequestMapping(value = "/quan-tri/nhan-vien", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("admin/employee/index");
		AccountDTO model = new AccountDTO();
		model.setPage(1);
		model.setSearchValue("");
		model.setLimit(5);
		mav.addObject("model", model);
		List<FeedBackDTO> dtoes = feedBackService.findByStatus();
		mav.addObject("feedbacks", dtoes);
		mav.addObject("countfeedback", dtoes.size());
		return mav;
	}

	@RequestMapping(value = "/quan-tri/nhan-vien/tim-kiem", method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute("model") AccountDTO dto) {
		dto.setLimit(dto.getLimit());
	    dto.setPage(dto.getPage());
	    dto.setSearchValue(dto.getSearchValue());
	    Pageable pageable = new PageRequest(dto.getPage() - 1, dto.getLimit());
	    List<AccountDTO> dtos;
	    RoleEntity role = new RoleEntity();
	    role.setId(SystemConstant.EMPLOYEE_ROLE);
	    if (!dto.getSearchValue().equals("")) {
	    	List<AccountDTO> employees= accountService.findByFullNameContainingAndRoles(dto.getSearchValue(),role, pageable);
	    	for (AccountDTO employee : employees) {
				if(employee.getImage()==null || employee.getImage().isEmpty())
				{
					employee.setImage(nophoto);
				}
			}
	    	dto.setListResult(employees);
		    dto.setTotalItem(dto.getListResult().size());
		    dto.setTotalPage((int) Math.ceil((double) dto.getTotalItem() / dto.getLimit()));
	    } else {
	        dtos = accountService.findAllEmployee(pageable);
	        for (AccountDTO employee : dtos) {
				if(employee.getImage()==null || employee.getImage().isEmpty())
				{
					employee.setImage(nophoto);
				}
			}
	        dto.setListResult(dtos);
		    dto.setTotalItem(accountService.getCountEmployee());
		    dto.setTotalPage((int) Math.ceil((double) dto.getTotalItem() / dto.getLimit()));
	    }

	    ModelAndView mav = new ModelAndView("admin/employee/search");
	    return mav;
	}


	@RequestMapping(value = "/quan-tri/nhan-vien/them", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView("admin/employee/edit");
		AccountDTO dto = new AccountDTO();
		dto.setId(null);
		dto.setAccountName("");
		mav.addObject("model", dto);
		return mav;
	}
	
	@RequestMapping(value = "/quan-tri/nhan-vien/chinh-sua", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(name = "id", required = false) Integer id) {
		ModelAndView mav = new ModelAndView("admin/employee/edit");
		AccountDTO dto = new AccountDTO();
		AccountEntity entity = new AccountEntity();
		
		System.out.println(id);
		//lấy id về để hiển thị qua trang sửa
		if (id != null) {
			entity = accountService.findById(id);
			dto = AccountConverter.toDTO(entity);
			if(dto.getImage()==null || dto.getImage().isEmpty())
			{
				dto.setImage(nophoto);
			}
		}
		mav.addObject("model", dto);
		return mav;
	}
	@RequestMapping(value = "/quan-tri/nhan-vien/xoa", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(name = "id", required = false) Integer id) {
		accountService.deleteAccount(id);
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("redirect:/quan-tri/nhan-vien");
	    return modelAndView;
	}
	@RequestMapping(value = "/quan-tri/nhan-vien/luu", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("model") @Valid AccountDTO dto,BindingResult result, ModelMap modelMap) {
		ModelAndView mav = new ModelAndView();
		//update thong tin phong
		if (result.hasErrors() || dto.getAccountName().equals("")){
			if(dto.getAccountName().equals(""))
				result.rejectValue("accountName", "notempty", "Tên đăng nhập không được bỏ trống!");
			System.out.println(result.hasErrors());
			mav.setViewName("admin/employee/edit");
			mav.addObject("model", dto);
			return mav;
		}
		else {
			if(dto.getId()!=null)
			{
				toImage(dto.getPhoto(), dto);
				accountService.updateAccount(dto);
			}
			else {
				//Cấp sẵn mật khẩu là 123456
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
				String hashedPassword = encoder.encode("123456");
				dto.setPassword(hashedPassword);
				
				toImage(dto.getPhoto(), dto);
				Integer id = accountService.insertUser(dto);
				accountService.assignRoleToAccount(id, SystemConstant.EMPLOYEE_ROLE);
			}
			
			mav.setViewName("redirect:/quan-tri/nhan-vien");
		}
		//cập nhật ảnh của người đăng nhập
		if(SecurityUtils.getPrincipal().getId() == dto.getId()) {
			securityUtils.getPrincipal().setImg(dto.getImage());
			securityUtils.getPrincipal().setFullname(dto.getFullName());
		}
	    return mav;
	}
	
	@RequestMapping(value = "quan-tri/thong-tin")
	public ModelAndView thongtin(@RequestParam(name="id", required = false) Integer id, @ModelAttribute("model") AccountDTO dto) {
		ModelAndView mav = new ModelAndView();
		//AccountDTO dto = new AccountDTO();
		AccountEntity entity = new AccountEntity();
		
		//Xử lý đổi pass:
		System.out.println("pass"+dto.getPassword()+"-"+dto.getNewPassword()+"-");

		if(dto.getPassword() != null ) {
			Map<String, String >result = messageUtil.getMessageChangePassword(dto);
			mav.addObject("message", result.get("message"));
			mav.addObject("alert", result.get("alert"));
			if(result.get("alert").equals("success")) { 
				accountService.changePassword(dto.getNewPassword(), id);
				mav.setViewName("admin/employee/infor");
			
			} else { 
				mav.setViewName("admin/employee/infor");
			}
		}
		else {
			mav.setViewName("admin/employee/infor");
		}
		
		//lấy id về để hiển thị qua trang sửa
		if (id != null) {
			entity = accountService.findById(id);
			dto = AccountConverter.toDTO(entity);
			if(dto.getImage()==null || dto.getImage().isEmpty())
			{
				dto.setImage(nophoto);
			}
		}
		mav.addObject("model", dto);
		return mav;
	}
	
	private void toImage(MultipartFile img,AccountDTO dto) {
		if(img==null || img.isEmpty()) {
			dto.setImage(dto.getImage());
		}
		else {
			String image = "/template/admin/img/account/"+multipartFile.saveFileAccount(img);
			dto.setImage(image);
		}
	}
}
