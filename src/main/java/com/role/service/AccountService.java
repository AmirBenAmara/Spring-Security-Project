package com.role.service;

import com.role.entities.AppRole;
import com.role.entities.AppUser;

public interface AccountService {
	
	public AppUser saveUser(String username, String password, String confirmedPassword);
	public AppRole saveRole(AppRole role);
	public AppUser loadUserByUsername(String username);
	public void AddRoleToUser(String username, String rolename);
	
}
