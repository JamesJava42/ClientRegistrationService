package com.client.service.model;

import java.security.Key;

import org.springframework.stereotype.Component;
@Component
public class Keydata {
	
	private Key publick;
	private Key privatek;
	public Key getPublick() {
		return publick;
	}
	public void setPublick(Key publick) {
		this.publick = publick;
	}
	public Key getPrivatek() {
		return privatek;
	}
	public void setPrivatek(Key privatek) {
		this.privatek = privatek;
	}
	public Keydata(Key publickey, Key privatekey) {
		super();
		this.publick = publickey;
		this.privatek = privatekey;
	}
	
	public Keydata() {}


}
