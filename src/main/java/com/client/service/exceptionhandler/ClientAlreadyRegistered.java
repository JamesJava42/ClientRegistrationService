package com.client.service.exceptionhandler;

import org.springframework.stereotype.Component;

@Component
public class ClientAlreadyRegistered  extends RuntimeException{
	
	public ClientAlreadyRegistered() {
		super();
	}

}
