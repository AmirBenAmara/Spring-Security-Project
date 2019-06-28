package com.role.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.role.dao.AppRoleRepository;
import com.role.dao.AppUserRepository;
import com.role.entities.AppRole;
import com.role.entities.AppUser;
@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	private AppUserRepository appUserRepository;
    private  AppRoleRepository appRoleRepository;
	
    public AccountServiceImpl(BCryptPasswordEncoder bcryptPasswordEncoder, AppRoleRepository appRoleRepository,
			AppUserRepository appUserRepository) {
		super();
		this.bcryptPasswordEncoder = bcryptPasswordEncoder;
		this.appRoleRepository = appRoleRepository;
		this.appUserRepository = appUserRepository;
	}

	
	
	@Override
	public AppUser saveUser(String username, String password, String confirmedPassword) {
			AppUser user=appUserRepository.findByUsername(username) ;
			if(user!=null) throw new RuntimeException("Already exist");
			if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
		AppUser appuser=new AppUser();
		appuser.setUsername(username);
		appuser.setPassword(bcryptPasswordEncoder.encode(password));
		appuser.setActived(true);
		appUserRepository.save(appuser);
		AddRoleToUser(username, "USER");
		return appuser;
	}

	@Override
	public AppRole saveRole(AppRole role) {
		
		return appRoleRepository.save(role);
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		return appUserRepository.findByUsername(username);
		 
	}

	@Override
	public void AddRoleToUser(String username, String rolename) {
		AppUser user=appUserRepository.findByUsername(username);
		AppRole role=appRoleRepository.findByRoleName(rolename);
		user.getRoles().add(role);
	}
	
	

}
