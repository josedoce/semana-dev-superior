package com.sds.bootcamp.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sds.bootcamp.app.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
