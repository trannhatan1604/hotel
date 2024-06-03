package com.hotel.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="orderstatus")
public class OrderStatusEntity extends BaseEntity {

	private String statusname;
	
	@OneToMany(mappedBy = "status")
	private List<OrderEntity> orders;

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	} 
	
}
