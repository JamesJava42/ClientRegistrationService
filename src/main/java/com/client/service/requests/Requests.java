package com.client.service.requests;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.client.service.dao.Userrepo;
import com.client.service.daoimpl.Clientimpl;
import com.client.service.exceptionhandler.Accessdenied;
import com.client.service.exceptionhandler.ClientAlreadyRegistered;
import com.client.service.exceptionhandler.ClientNotRegistered;
import com.client.service.exceptionhandler.NullPointer;
import com.client.service.model.Client;
import com.client.service.model.User;
import com.client.service.response.Response;
import com.client.service.security.Securityconfig;



@RestController
public class Requests {
	
	
	@Autowired
	Clientimpl clientimpl;
	@Autowired
	Securityconfig securityconfig;
	@Autowired
	Userrepo userrepo;
	
	Logger log = LoggerFactory.getLogger(Requests.class);
	
	@PostMapping("/")
	public Response addUsers(@RequestBody User  user){
		userrepo.save(user);
		return new Response("Created :"+user.getId(), HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public Response checkUser(HttpServletRequest req){
		String sreq  =  req.getHeader("Authorization");
		int idx = sreq.indexOf("Basic ");
		String sub = (String) sreq.subSequence(idx, sreq.length());
		String decode = sreq.split(" ")[1];
		byte[] text =    Base64.getDecoder().decode(decode);
		
		String out = new String(text);
		
		String username= out.split(":")[0];
		String password = out.split(":")[1];
		System.out.println("username :"+username+" Pass :"+password);
		
		UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(username, password);
        
		Authentication obj =  securityconfig.authProvider().authenticate(authtoken);
		
		
		if(obj.isAuthenticated()) {
			
			SecurityContextHolder.getContext().setAuthentication(obj);
			return new Response("Sucessfully Authenticated",HttpStatus.OK);
		}else {
			throw new Accessdenied();
		}
		}
		

		
	
	
	
	@PostMapping("/admin/clientadd")
	public Response addClient(@RequestBody Client client){
		
		try {	
		String id = clientimpl.addClient(client);
		
		
		return new Response(id,HttpStatus.OK);
		}catch(ClientAlreadyRegistered e) {
			throw new ClientAlreadyRegistered();
			//return new Response("unknow Exception : "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
		
	
	
	@GetMapping("/getkey")
	public Response getKey(@RequestParam String id,@RequestParam String secret,@RequestBody Client client){
		
		
		

		try {
		
		String publick = clientimpl.checkClient(client);
		
		HttpHeaders headers= new  HttpHeaders();
		
		if(publick == null) {
			return new Response("Client Not Registered ",HttpStatus.OK);
		}
		else {
			String keyresponse=clientimpl.checkHeaders(id,secret, client.getCid());
			log.info("returned from  to the checker method :"+keyresponse);
			if( ! (keyresponse ==null)) {
				headers.add("Public Key : ", keyresponse);
				return new Response(" "+ headers, HttpStatus.OK);
			}
		}
		
		return new Response("Data :"+headers,HttpStatus.OK);
		}catch(ClientNotRegistered ex) {
			throw new ClientNotRegistered(client.getCid());
		}catch(NullPointer ex) {
			throw new NullPointer();
		}
		
		
	}


}
