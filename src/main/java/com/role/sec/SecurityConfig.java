package com.role.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//WebSecurityConfigurerAdapter : class de configuration de spring security 
	// on va configurer AuthenticationManagerBuilder pour encoder le password 
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		
		/*auth.inMemoryAuthentication()
        .withUser("some@gmail.com")
        .password("12345")
        .role("USER");
        
        *
        *
        *   http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/**")
            .hasRole("USER")
            .andBasic();*/
	
	}
	
	// configurer les requetes http  
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.failureUrl("/login.html?error=true");
		
       
    	http.csrf().disable();
    	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	
    	//stateless to get the security context
    	//SecurityContext is used to store the details of the currently authenticated user,
    	//also known as a principle. So, if you have to get the username or any other user details,
    	//you need to get this SecurityContext first
       
        //http.formLogin();
		
        //http.authorizeRequests().anyRequest().permitAll();
		
		//authoriser les requetes vers /login /register pour tous le monde 
		http.authorizeRequests().antMatchers("/login/**","/register/**").permitAll(); 
		
		//authoriser les requetes vers /appUsers /appRoles pour les utilisateur avec role ADMIN 
		http.authorizeRequests().antMatchers("/appUsers/**","/appRoles/**").hasAuthority("ADMIN");
		
		http.authorizeRequests().anyRequest().authenticated();
		
		
		http.addFilter(new JWTAuthorizationFilter(authenticationManager()));
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
