package com.hotel.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "account")
public class AccountEntity extends BaseEntity{
	
	@Column(name = "accountname")
	private String accountName;

	@Column(name = "image")
	private String image;
	
	@Column(name = "fullname")
	private String fullName;

	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
	private String address;

	@Column(name = "status")
	private int status;

	@Column(name = "password")
	private String password;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<OrderEntity> orders = new ArrayList<>();;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<FeedBackEntity> feedbacks = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "account_role", //ten bang trung gian
		joinColumns = @JoinColumn(name = "accountid"), //de @ManyToMany ben userEntity thi phai bo joinColumn la userid truoc
		inverseJoinColumns = @JoinColumn(name = "roleid"))
	private List<RoleEntity> roles = new ArrayList<>();

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	
}

    
