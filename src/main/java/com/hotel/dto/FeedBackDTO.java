package com.hotel.dto;

import javax.validation.constraints.NotEmpty;

import com.hotel.entity.AccountEntity;
import com.hotel.entity.RoomEntity;

public class FeedBackDTO extends AbstractDTO<FeedBackDTO>{

	private AccountEntity account;
	
	private RoomEntity room;
	
	@NotEmpty(message = "Nội dung phản hồi chưa có, quý khách vui lòng thêm vào!")
	private String description;

	private int status;
	
	//chứa tên khách hàng feedback
	private String accountName;

	private int accountId;
	
	private String roomName;
	
	private int roomId;
	
	private String typeRoomName;
	
	private int orderId;
	
	
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getTypeRoomName() {
		return typeRoomName;
	}

	public void setTypeRoomName(String typeRoomName) {
		this.typeRoomName = typeRoomName;
	}

}
