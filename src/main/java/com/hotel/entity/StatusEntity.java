package com.hotel.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "status")
public class StatusEntity extends BaseEntity{

		@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	    private List<RoomEntity> rooms;
		
		@Column(name = "statusname")
		private String statusName;

		public List<RoomEntity> getRooms() {
			return rooms;
		}

		public void setRooms(List<RoomEntity> rooms) {
			this.rooms = rooms;
		}

		public String getStatusname() {
			return statusName;
		}

		public void setStatusname(String statusname) {
			this.statusName = statusname;
		}

		
		
}
