package com.examenbci.ejercicio1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examenbci.ejercicio1.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findById(long id);
	User findByEmail(String email);
}
