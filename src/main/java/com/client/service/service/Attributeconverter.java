package com.client.service.service;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;

import org.springframework.stereotype.Component;
@Component
public class Attributeconverter   implements AttributeConverter<String, String>{
	
	private static final String AES = "AES";
	private static final String SECRET = "secret-key-12345";
	
	
	private final Key key;
    private final Cipher cipher;
    
    
    public Attributeconverter() throws NoSuchAlgorithmException, NoSuchPaddingException {
    	  key = new SecretKeySpec(SECRET.getBytes(), AES);
          cipher = Cipher.getInstance(AES);
    }
    
    
    
	@Override
	public String convertToDatabaseColumn(String attribute) {
		try {
			
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return Base64.getEncoder().encodeToString(cipher.doFinal(attribute.getBytes()));
		}catch(Exception e) {
			throw new IllegalStateException("Encoding Exception");
		}
		
		
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
			System.out.println(dbData.length());
			return new String(cipher.doFinal(Base64.getDecoder().decode(dbData)));
			//return new String(cipher.doFinal(Base64.getDecoder().decode(dbData.getBytes())));
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			throw new IllegalStateException("Decoding Exeption "+e);
		}
	}

}
