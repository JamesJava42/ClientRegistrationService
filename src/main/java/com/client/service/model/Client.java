package com.client.service.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.client.service.service.Attributeconverter;

@Entity
@Table(name = "clients")
public class Client {
	

	@Id
	private int cid;
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "id")
	@Convert(converter = Attributeconverter.class)
	private String id;
	@Convert(converter= Attributeconverter.class)
	private String secret;
	private String ip;
	private String domain;
	private String webhook;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getWebhook() {
		return webhook;
	}
	public void setWebhook(String webhook) {
		this.webhook = webhook;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	
	

}
