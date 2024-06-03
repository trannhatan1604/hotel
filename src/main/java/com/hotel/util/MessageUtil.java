package com.hotel.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hotel.dto.AccountDTO;
import com.hotel.dto.RoomDTO;
import com.hotel.dto.TypeRoomDTO;
import com.hotel.entity.AccountEntity;
import com.hotel.service.IAccountService;

@Component
public class MessageUtil {
	
	@Autowired
	private IAccountService accountService;
	
	public Map<String, String> getMessageRoom(RoomDTO dto) {
		Map<String, String> result = new HashMap<>();
		if (dto.getName()==null||dto.getName().isEmpty()) {
			result.put("message", "Thất bại! Thiếu tên phòng!");
			result.put("alert", "danger");
		} else if (dto.getStatusid() == 0 || dto.getStatusid() == null) {
			result.put("message", "Thất bại! Thiếu trạng thái!");
			result.put("alert", "danger");
		} else if (dto.getTypeid() == null || dto.getTypeid() == 0) {
			result.put("message", "Thất bại! Thiếu loại phòng!");
			result.put("alert", "danger");
		} else {
			result.put("message", "Cập nhật thông tin thành công!");
			result.put("alert", "success");
		}
		return result;
	}
	
	public Map<String, String> getMessageTypeRoom(TypeRoomDTO dto) {
		Map<String, String> result = new HashMap<>();
		if (dto.getName()==null||dto.getName().isEmpty()) {
			result.put("message", "Thất bại! Thiếu tên loại phòng!");
			result.put("alert", "danger");
		}else if (dto.getPriceFormat().equals("0.0") ) {
			result.put("message", "Thất bại! Chưa điền giá!");
			result.put("alert", "danger");
		}else if (dto.getPrice()<0) {
		    result.put("message", "Thất bại! Giá không hợp lệ!");
		    result.put("alert", "danger");
		}else {
			result.put("message", "Cập nhật thông tin thành công!");
			result.put("alert", "success");
		}
		return result;
	}
	public Map<String, String> getMessageChangePassword(AccountDTO dto) {
		Map<String, String> result = new HashMap<>();
		boolean checkpass = dto.getNewPassword().equals(dto.getRepeatPassword());
		boolean dtoCheck = accountService.findByIdAndPassword(SecurityUtils.getPrincipal().getId(), dto.getPassword());
		System.out.println(dtoCheck);
		if ((dto.getPassword()==null && dto.getPassword().equals("")) ) {
			result.put("message", "Thất bại! Mật khẩu không bỏ trống!");
			result.put("alert", "danger");
		}else if (dto.getNewPassword().length()<6 ) {
			result.put("message", "Thất bại! Mật khẩu phải có ít nhất 6 ký tự");
			result.put("alert", "danger");
		}else if (!checkpass ) {
			result.put("message", "Thất bại! Mật khẩu mới không trùng khớp");
			result.put("alert", "danger");
		}else if (!dtoCheck) {
			result.put("message", "Thất bại! Mật khẩu cũ không đúng");
			result.put("alert", "danger");
		}
		else {
			result.put("message", "Cập nhật thông tin thành công!");
			result.put("alert", "success");
		}
		return result;
	}
}
