package com.client.service.exceptionhandler;

import org.springframework.stereotype.Component;

@Component
public class Accessdenied  extends RuntimeException{
	public Accessdenied() {
		super();
	}

}
