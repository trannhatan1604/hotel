package com.hotel.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import com.hotel.entity.AccountEntity;
import com.hotel.valid.FutureOrPresentAndNotNull;
import com.hotel.valid.MaxPeople;

@MaxPeople(checkField="quantity", requiredField="roomId", message="Số lượng người vượt quá số lượng tối đa của phòng!")
@FutureOrPresentAndNotNull(checkField1="checkinDate", checkField2 = "checkoutDate", requiredField = "id",message="Vui long chon ngay khac!")
public class OrderDTO extends AbstractDTO<OrderDTO> {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date checkinDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date checkoutDate;

	@NotNull(message = "Vui lòng nhập số lượng!")
	@Positive(message = "Số lượng phải là số nguyên dương!")
	//truong can valid so luong
	private Integer quantity;

	private Float totalPrice;
	
	private Float oldPrice;
	
	private Float newPrice;

	private Integer customerId;
	
	private Integer employeeId;

	private AccountEntity Customer;

	private AccountEntity Employee;

	private Integer promotionLevel;
	
	private Integer statusId;
	
	private String roomName;
	
	//ma cua phong co so luong toi da
	@NotNull(message = "Vui lòng chọn phòng!")
	private Integer roomId;
	
	private Integer promotionId;

	public Date getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}

	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public AccountEntity getCustomer() {
		return Customer;
	}

	public void setCustomer(AccountEntity customer) {
		Customer = customer;
	}

	public AccountEntity getEmployee() {
		return Employee;
	}

	public void setEmployee(AccountEntity employee) {
		Employee = employee;
	}

	public Integer getPromotionLevel() {
		return promotionLevel;
	}

	public void setPromotionLevel(Integer promotionLevel) {
		this.promotionLevel = promotionLevel;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}

	public void convertCheckInDate(String checkinDateStr) {
		System.out.println(checkinDateStr);
		 // Định dạng của chuỗi đầu vào
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz uuuu")
                                                       .withLocale(Locale.US);

        // Chuyển chuỗi thành ZonedDateTime
        ZonedDateTime zdt = ZonedDateTime.parse(checkinDateStr, formatter);

        // Chuyển ZonedDateTime thành LocalDate
        LocalDate localDate = zdt.toLocalDate();

        System.out.println("LocalDate: " + localDate);
        
		/*
		 * // Chuyển LocalDate thành java.sql.Date LocalDate date =
		 * LocalDate.parse(localDate.toString()); Date dateConverter =
		 * Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		 */
        // Lưu trữ giá trị vào this.checkinDate
        try {
			Date date = java.sql.Date.valueOf(localDate);
			System.out.println("DD"+date);
			 this.checkinDate = date;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Converted Date: " + this.checkinDate);
    }
	public void convertCheckOutDate(String checkoutDateStr) {
	        // Định dạng của chuỗi đầu vào
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz uuuu")
	                                                       .withLocale(Locale.US);

	        // Chuyển chuỗi thành ZonedDateTime
	        ZonedDateTime zdt = ZonedDateTime.parse(checkoutDateStr, formatter);
	        // Chuyển ZonedDateTime thành LocalDate
	        LocalDate localDate = zdt.toLocalDate();
	        System.out.println("LocalDate: " + localDate.toString());
			
			try {
				Date date = java.sql.Date.valueOf(localDate);
				System.out.println("DD"+date);
				 this.checkoutDate = date;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        // Lưu trữ giá trị vào this.checkinDate
	       
	        System.out.println("Converted Date: " + this.checkoutDate);
	    
	}

	public Float getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(Float oldPrice) {
		this.oldPrice = oldPrice;
	}

	public Float getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(Float newPrice) {
		this.newPrice = newPrice;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	
	
	public OrderDTO() {
		super();
	}

	public OrderDTO(Date checkinDate, Date checkoutDate,
			Integer quantity,
			Float totalPrice, Integer customerId, Integer employeeId, Integer statusId,
			Integer roomId, Integer promotionId) {

		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.customerId = customerId;
		this.employeeId = employeeId;
		this.statusId = statusId;
		this.roomId = roomId;
		this.promotionId = promotionId;
	}



}
