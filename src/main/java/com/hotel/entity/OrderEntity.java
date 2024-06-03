package com.hotel.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {
	

	@ManyToOne
    @JoinColumn(name = "employee_id")
    private AccountEntity account;
	
	@ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;
	
	@ManyToOne
    @JoinColumn(name = "promotion_id")
    private PromotionEntity promotion;
	
	@ManyToOne
	@JoinColumn(name="status_id")
	private OrderStatusEntity status;
	
	@Column(name = "checkindate")
	private Date checkinDate;

	@Column(name = "checkoutdate")
	private Date checkoutDate;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "totalprice")
	private float totalPrice;
	
/*	@Column(name = "status")
	private boolean status;*/
	
	@Column(name = "customer_id")
	private  int customerId;

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public RoomEntity getRoom() {
		return room;
	}

	public void setRoom(RoomEntity room) {
		this.room = room;
	}

	public PromotionEntity getPromotion() {
		return promotion;
	}

	public void setPromotion(PromotionEntity promotion) {
		this.promotion = promotion;
	}

	public Date getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(java.util.Date checkinDate) {
		 if (checkinDate != null) {
		        this.checkinDate = new java.sql.Date(checkinDate.getTime());
		    } else {
		        this.checkinDate = null;
		    }
	}

	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(java.util.Date checkoutDate) {
		 if (checkoutDate != null) {
		        this.checkoutDate = new java.sql.Date(checkoutDate.getTime());
		    } else {
		        this.checkoutDate = null;
		    }
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	/*public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}*/

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public OrderStatusEntity getStatus() {
		return status;
	}

	public void setStatus(OrderStatusEntity status) {
		this.status = status;
	}
}
