package com.hotel.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.dto.AccountDTO;
import com.hotel.service.IAccountService;

@RestController
public class OrderAPI {
	@Autowired
	private IAccountService accountService;

	@PostMapping(value = "/quan-tri/dat-phong/tim-khach-hang")
	public AccountDTO createNew(@RequestBody String phoneNumber) { // Nhan request la mot chuoi JSON
		System.out.println(phoneNumber);
		if (phoneNumber != null && phoneNumber.contains("=")) {
			AccountDTO model = new AccountDTO();
			String[] parts = phoneNumber.split("=");
			if (parts.length >= 2) {
				phoneNumber = parts[1];
				try {
					System.out.println("Số điện thoại: " + phoneNumber.trim());
					if (phoneNumber.trim() == "") {
						System.out.println("Số điện thoại null");
						model.setMessage("Vui lòng nhập số điện thoại!");
						return model;
					} else {
						// Regex cho số điện thoại Việt Nam
						if (!phoneNumber.matches("^(0[3|5|7|8|9])+([0-9]{8})$")) {
							System.out.println("Số điện thoại không hợp lệ!");
							model.setMessage("Số điện thoại không hợp lệ!");
							return model;
						}
						model = accountService.findCustomerByPhone(phoneNumber.trim());
						System.out.println("Name người dùng: " + model.getFullName());
						return model;
					}
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}else {
				System.out.println("Số điện thoại null 2");
				model.setMessage("Vui lòng nhập số điện thoại!");
				return model;
			}
		}
		return new AccountDTO();
	}
}
