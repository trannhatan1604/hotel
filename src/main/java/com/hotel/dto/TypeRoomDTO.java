package com.hotel.dto;

import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

import com.hotel.entity.RoomEntity;

public class TypeRoomDTO extends AbstractDTO<TypeRoomDTO>{
	
	private List<RoomEntity> rooms;
	
	@NotEmpty(message = "Vui lòng nhập tên loại phòng")
	private String name;

	private int quantity;
	
	private String image;
	
	private int space;

	private String description;
	
	//@DecimalMin(value = "0.0" , inclusive = true, message = "Giá phòng phải lớn hơn 0")
	private float price;

	private String dateRange;
	
	private float percent;
	public float getPercent() {
		return percent;
	}
	public void setPercent(float percent) {
		this.percent = percent;
	}
	
	public List<RoomEntity> getRooms() {
		return rooms;
	}

	public void setRooms(List<RoomEntity> rooms) {
		this.rooms = rooms;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getSpace() {
		return space;
	}

	public void setSpace(int space) {
		this.space = space;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateRange() {
		return dateRange;
	}

	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}
	
	
}
