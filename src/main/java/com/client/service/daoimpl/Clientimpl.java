package com.client.service.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.client.service.dao.Clientrepo;
import com.client.service.exceptionhandler.ClientNotRegistered;
import com.client.service.exceptionhandler.NullPointer;
import com.client.service.keys.Keysgen;
import com.client.service.model.Client;
import com.client.service.model.Keydata;
import com.client.service.response.Response;
import com.client.service.service.Attributeconverter;

@Component
public class Clientimpl {
	

	@Autowired
	Clientrepo clientrepo;
	@Autowired
	Keysgen keysgen;
	@Autowired
	Attributeconverter attributeconverter;
	
	Logger log = LoggerFactory.getLogger(Clientimpl.class);
	
	
	public  String checkClient(Client client) {
		
		 final Map<Integer,Keydata> out = keysgen.mdata;
	
		Client user = clientrepo.getClient(client.getCid());
		System.out.println(" out size :"+out.size() + " user : "+user.getCid());
		
		if(user != null) {
			try {
			
			Keydata key = out.get(user.getCid());
			
			
			if(key != null) {
			   
			        return key.getPublick().toString();
			}
			}catch(NullPointerException e) {
				throw new NullPointer();
			}
		
			
		}else {
			
	
		    throw new ClientNotRegistered(client.getCid());
	}
		return ""+client.getCid();
		
	}
	
	
	public String addClient(Client client) {
		String pkey = keysgen.genAndUpdateKeys(client.getCid());
		
		clientrepo.save(client);
		return "Key and id "+client.getId()+pkey;
		
	}
	
	public  List<Client>  getDetails(){
		List<Client> data =  clientrepo.findAll();
		if(data != null) {
			return data;
		}
		else {
			List<Client> out = new ArrayList<>();
			return out;
		}
		
		
	}
	
	public String checkHeaders( String id,String secret,int cid) {
		
		
		Client client = clientrepo.getClient(cid);
		if(client == null) {
			throw new ClientNotRegistered(cid);
		}
		

		String eid= client.getId();
		String esecret=client.getSecret();

		
		//System.out.println(eid+ " secret :"+esecret + "is empty :"+ client.getSecret().toString().equals( esecret));
		if( client != null && client.getSecret().toString().equals( secret) && client.getId().toString().equals(id)) {
			
			final Map<Integer,Keydata> res = keysgen.mdata;
			log.info("Enterte to the checker main : "+eid + " :  " + esecret+ "Size is :"+res.size());
			
			
			for(java.util.Map.Entry<Integer, Keydata> it : res.entrySet()) {
				
				if(it.getKey()==cid) {
					
					Keydata data = it.getValue();
					if(data != null) {
						return data.getPublick().toString();
						
					}
					
				}
				
			}
		}
		log.info("not entered ");

		return null;
		
		
	}
	
	
	
//	 log.info("The map size is : "+out.size());
//	
////	Keydata  keydata=   out.get(client.getCid());
//
	

}
