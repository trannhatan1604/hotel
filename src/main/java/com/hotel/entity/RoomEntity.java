package com.hotel.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "room")
public class RoomEntity extends BaseEntity {
	
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<OrderEntity> orders;
	
	@OneToMany(mappedBy = "room") 
	private List<FeedBackEntity> feedbacks = new ArrayList<>();
	
	@ManyToOne
    @JoinColumn(name = "status_id")
    private StatusEntity status;
	
	@ManyToOne
    @JoinColumn(name = "typeroom_id")
    private TypeRoomEntity typeroom;
	
	@Column(name = "name")
	private String name;


	@Column(name = "ratequantity")
	private int rateQuantity;

	@Column(name = "image")
	private String image;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRatequantity() {
		return rateQuantity;
	}

	public void setRatequantity(int ratequantity) {
		this.rateQuantity = ratequantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}

	public StatusEntity getStatus() {
		return status;
	}

	public void setStatus(StatusEntity status) {
		this.status = status;
	}

	public TypeRoomEntity getTyperoom() {
		return typeroom;
	}

	public void setTyperoom(TypeRoomEntity typeroom) {
		this.typeroom = typeroom;
	}
	
	
}
