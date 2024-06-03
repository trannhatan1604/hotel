    package com.hotel.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.hotel.entity.FeedBackEntity;
import com.hotel.entity.OrderEntity;
import com.hotel.entity.RoleEntity;

public class AccountDTO extends AbstractDTO<AccountDTO>{

	private List<OrderEntity> orders;
	
	private List<FeedBackEntity> feedbacks = new ArrayList<>();
	
	private List<RoleEntity> roles = new ArrayList<>();
	
	/* @NotEmpty(message = "Tên tài khoản không được để trống") */
	private String accountName;
	
	@NotEmpty(message = "Họ tên không được để trống")
	private String fullName;
	
	private String image;
	
	@Pattern(regexp = "^(0[3|5|7|8|9])+([0-9]{8})$",message = "Số điện thoại sai định dạng")
	private String phone;

	private String address;

	private int status;
	
	private String password;
	
	private String repeatPassword;
	
	private String newPassword;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}

	public List<FeedBackEntity> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<FeedBackEntity> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}

    
