package com.role;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.role.entities.AppRole;
import com.role.service.AccountService;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
	
	
	/*@Bean
	CommandLineRunner start(AccountService accountService){
			return args->{
				accountService.saveRole(new AppRole("USER"));
				accountService.saveRole(new AppRole("ADMIN"));
				
				Stream.of("user1","user2","user3","admin").forEach(un->{
				accountService.saveUser(un,"111", "111");
					System.out.println(accountService.loadUserByUsername(un).getPassword());
					
				});
				
				accountService.AddRoleToUser("admin", "ADMIN");

			};
	}*/
	
	@Bean
	BCryptPasswordEncoder getBCPE() {
		
		return new BCryptPasswordEncoder();
	}
	
	
}

