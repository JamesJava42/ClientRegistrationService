package com.client.service.users;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.client.service.dao.Userrepo;
import com.client.service.exceptionhandler.Accessdenied;
import com.client.service.model.User;


@Component
public class Userdetailservice implements UserDetailsService {

	@Autowired
	Userrepo userrepo;
	
	Logger log = org.slf4j.LoggerFactory.getLogger(Userdetailservice.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Entering the USer detail class"+username);
		User users = userrepo.getUser(username);
		

		if(users == null) {
			throw new Accessdenied();
		}else {
			log.info("returnted the user objct from the repo"+users.getName());
			return new Myuserdetails(users);
		}
		
		
	}

}
