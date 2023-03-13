package com.client.service.keys;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.client.service.exceptionhandler.ClientAlreadyRegistered;
import com.client.service.model.Keydata;


@Component
public class Keysgen {
	
	@Autowired
	public Keydata keydata;

	static int cid;
	public static Map<Integer, Keydata> mdata = new HashMap<>();

	public Keysgen(int id) {
		this.cid = id;
	}

	
	  public Keysgen(){ }
	  
	 
	public static String genAndUpdateKeys(int cid) {
		try {
			 KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			  generator.initialize(1024); 
			  KeyPair keyPair= generator.generateKeyPair(); 
			  Key publickey = keyPair.getPublic(); 
			  Key privatekey = keyPair.getPrivate();
			  
			
			  Keydata obj = new Keydata(publickey,privatekey);
//			  Map<Integer,Keydata> mdata = new HashMap<>();
			  if(mdata.containsKey(cid))
			  { 
//				  Keydata keydata = mdata.get(cid);
//			     keydata.setPrivatek(privatekey);
//			     keydata.setPublick(publickey);
//			  
//			      mdata.put(cid, keydata);
//			     
			     throw new ClientAlreadyRegistered();
			  
			  
			  
			  }else { 
			   Keydata keydata = new Keydata();
			   keydata.setPrivatek(privatekey);
			  keydata.setPublick(publickey);
			  mdata.put(cid,keydata );
			 
			  return mdata.get(cid).getPublick().toString();
			  
			  }
			  
			  
			  
			  
			  
			  
			  } catch (NoSuchAlgorithmException e) {
			        throw new IllegalStateException("Error in creating hte keys" +e.getLocalizedMessage());
			  }
			  
			  
		

	
	}


}
