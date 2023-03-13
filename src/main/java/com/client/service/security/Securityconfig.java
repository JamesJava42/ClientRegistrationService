package com.client.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import com.client.service.users.Userdetailservice;

@EnableWebSecurity
@Configuration
@Component
public class Securityconfig {

	@Autowired
	Userdetailservice userdetailservice;
	
	 @Bean
	    public SecurityFilterChain chain(HttpSecurity http) throws Exception {
		 http.csrf().disable();
		 
	     http.authorizeRequests()
	    	 
	    	 .antMatchers("/admin/**").hasAuthority("ADMIN")
	    	 .antMatchers("/getkey").hasAnyAuthority("USER","ADMIN")
	    	 .antMatchers("/login","/").permitAll()
	    	 .anyRequest().authenticated();
	    	
	    	//.anyRequest().authenticated();
	    
	    	
	    	
	    	return http.build();
	    
	    }
	
	
	
	@Primary
    @Bean
    public AuthenticationManagerBuilder authBuilder(AuthenticationManagerBuilder authBuilder) {
		System.out.println("ntered to the authbuilder :");

    	return authBuilder.authenticationProvider(authProvider());
    }
	@Bean
	public DaoAuthenticationProvider authProvider() {
		System.out.println("ntered to the authprovider :");

	       DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	       authenticationProvider.setUserDetailsService(userdetailservice);
	       authenticationProvider.setPasswordEncoder(passwordEncoder());
	       return authenticationProvider;
	}
    
   
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
     


}
