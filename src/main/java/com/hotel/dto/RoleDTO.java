package com.hotel.dto;

import java.util.ArrayList;
import java.util.List;

import com.hotel.entity.AccountEntity;

public class RoleDTO extends AbstractDTO<RoleDTO>{

	private List<AccountEntity> users = new ArrayList<>();

	private String namerole;

	public List<AccountEntity> getUsers() {
		return users;
	}

	public void setUsers(List<AccountEntity> users) {
		this.users = users;
	}

	public String getNamerole() {
		return namerole;
	}

	public void setNamerole(String namerole) {
		this.namerole = namerole;
	}

	
}
