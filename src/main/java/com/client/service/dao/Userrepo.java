package com.client.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.client.service.model.User;



@Repository
public interface Userrepo extends JpaRepository<User, Integer> {
	@Query("select u From User u where u.name = ?1")
	//@Query(value="select * from users where name = ?1" ,nativeQuery = true)
	public User getUser(String admin);

}
