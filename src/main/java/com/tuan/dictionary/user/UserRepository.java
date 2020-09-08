package com.tuan.dictionary.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findByEmail(String email);
	
	List<User> findByPhoneNumber(String phoneNumber);
	
}
