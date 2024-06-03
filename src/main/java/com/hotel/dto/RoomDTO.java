package com.hotel.dto;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.hotel.entity.FeedBackEntity;
import com.hotel.entity.OrderEntity;

public class RoomDTO extends AbstractDTO<RoomDTO> {

	private List<OrderEntity> orders;

	private List<FeedBackEntity> feedbacks = new ArrayList<>();

	private String statusname;

	private String typename;

	@NotEmpty(message = "Vui lòng nhập tên phòng")
	private String name;

	private int ratequantity;

	private String image;

	private float price;
	
	private String description;

	private int quantity;
	// Checkin checkout
	private Date checkinDate;
	private Date checkoutDate;
	
	private String dateRange;
private List<String> listCheckinDates;
	
	private List<String> listCheckoutDates;
	@NotNull(message = "Vui lòng chọn loại phòng!")
	private Integer typeid;

	@NotNull(message = "Vui lòng chọn trạng thái!")
	private Integer statusid;

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

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRatequantity() {
		return ratequantity;
	}

	public void setRatequantity(int ratequantity) {
		this.ratequantity = ratequantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public Integer getStatusid() {
		return statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	public String getDateRange() {
		return dateRange;
	}

	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}

	public Date getCheckinDate() {
		if (this.dateRange.equals(""))
			return null;
		String[] times = this.dateRange.split(" - ");
		if (times.length == 2) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			try {
				Date checkinDate = new Date(format.parse(times[0]).getTime());

				return checkinDate;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;
	}

	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}

	public Date getCheckoutDate() {
		if (this.dateRange.equals(""))
			return null;
		String[] times = this.dateRange.split(" - ");
		if (times.length == 2) {
			// Sử dụng DateTimeFormatter để chuyển đổi từ chuỗi sang LocalDate
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			try {
				Date checkoutDate = new Date(format.parse(times[1]).getTime());

				return checkoutDate;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return checkoutDate;
		}

		return null;
	}

	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public List<String> getListCheckinDates() {
		return listCheckinDates;
	}

	public void setListCheckinDates(List<String> listCheckinDates) {
		this.listCheckinDates = listCheckinDates;
	}

	public List<String> getListCheckoutDates() {
		return listCheckoutDates;
	}

	public void setListCheckoutDates(List<String> listCheckoutDates) {
		this.listCheckoutDates = listCheckoutDates;
	}
}
