package com.client.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.client.service.model.Client;


@Repository
public interface Clientrepo extends JpaRepository<Client,Integer>{


	@Query("select u from Client u where u.cid = ?1 ")
	public Client getClient(int id);

	
}
