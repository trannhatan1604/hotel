package com.hotel.dto;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.hotel.entity.OrderEntity;

public class PromotionDTO extends AbstractDTO<PromotionDTO> {
	private List<OrderEntity> orders;

	@NotEmpty(message = "Tên khuyến mãi không được bỏ trống")
	private String name;

	private int level;

	private Date startdate;

	private Date enddate;

	private String description;

	private String dateRange;

	private String status;

	private boolean isAllowDelete;

	public boolean getAllowDelete() {
		return isAllowDelete;
	}

	public void setAllowDelete(boolean isAllowDelete) {
		this.isAllowDelete = isAllowDelete;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
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
