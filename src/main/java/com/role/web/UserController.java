package com.role.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.role.entities.AppUser;
import com.role.service.AccountService;

@RestController
public class UserController {
	@Autowired
    private AccountService accountService;
	
	@PostMapping("/register")
	public AppUser register(@RequestBody UserForm userForm) {
		
		return accountService.saveUser(userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPassword());
		
	}
	
	
	static class UserForm{
		private String username;
		private String password;
		private String confirmedPassword;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getConfirmedPassword() {
			return confirmedPassword;
		}
		public void setConfirmedPassword(String confirmedPassword) {
			this.confirmedPassword = confirmedPassword;
		}
		public UserForm() {
			super();
			// TODO Auto-generated constructor stub
		}
		public UserForm(String username, String password, String confirmedPassword) {
			super();
			this.username = username;
			this.password = password;
			this.confirmedPassword = confirmedPassword;
		}
		
		
	}
}
