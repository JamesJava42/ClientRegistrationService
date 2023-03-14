package com.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import com.client.service.daoimpl.Clientimpl;
import com.client.service.keys.Keysgen;
import com.client.service.model.Client;

@SpringBootApplication
public class ServiceApplication {
	
	@Autowired
	Keysgen keysgen;
	
	@Autowired
	Clientimpl clientimpl;

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}
	
	@EventListener
	public   void handleEvent(ContextRefreshedEvent contextRefreshedEvent) {
		List<Client> out= clientimpl.getDetails();
		for(Client c : out) {
			System.out.println("Updateing the keys in client id "+c.getCid());
			keysgen.genAndUpdateKeys(c.getCid());
			
		}
		
		
		
		
	}

}
