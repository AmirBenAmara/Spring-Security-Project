package com.role.sec;

import java.util.ArrayList;
import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.role.entities.AppUser;
import com.role.service.AccountService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	
	//UserDetailsService interface predefini par spring security 
	//implementer UserDetailService(springSecurity) pour redifinir loadUserByUsername

	@Autowired
	private AccountService accountService;//injection de AccountService pour utiliser notre methode loadUserByUsername
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser=accountService.loadUserByUsername(username);
		//System.out.println(appUser.getUsername());
		
		if(appUser==null) throw new UsernameNotFoundException("invalid User"); // si user néexiste pas 
		
		Collection<GrantedAuthority> authorities=new ArrayList<>();
		appUser.getRoles().forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		});
		return new User(appUser.getUsername(), appUser.getPassword(),authorities);
		//retourner un objet User(springSecurity) avec les données de mon objet AppUser
	}

}
