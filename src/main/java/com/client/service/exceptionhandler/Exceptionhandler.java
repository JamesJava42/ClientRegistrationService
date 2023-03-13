package com.client.service.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.client.service.response.Response;
@RestControllerAdvice
public class Exceptionhandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(ClientNotRegistered.class)
	public Response handleClient(ClientNotRegistered ex , WebRequest req){
		return new Response("Client is Not Reistered , Please Register with CLient Details ", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ClientAlreadyRegistered.class)
	public Response handleClientExeption(ClientAlreadyRegistered ex, WebRequest req ) {
		return new Response("Client ALready Registered ", HttpStatus.FOUND);
	}
	
	@ExceptionHandler(NullPointer.class)
	public Response handleNull(NullPointer ex, WebRequest req) {
		return new Response("Null Exception in Clientsimple :", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Accessdenied.class)
	public Response handleDenied(Accessdenied ex, WebRequest req) {
		return new Response("Admin Access Denied , Incorrect cred's :",HttpStatus.UNAUTHORIZED);
	}

}
