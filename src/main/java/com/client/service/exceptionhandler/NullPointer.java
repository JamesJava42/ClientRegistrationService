package com.client.service.exceptionhandler;

import org.springframework.stereotype.Component;

@Component
public class NullPointer  extends RuntimeException{
	
	public NullPointer() {
		super();
	}

}
