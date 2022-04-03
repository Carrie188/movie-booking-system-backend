package com.example.demo.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByUserName(String userName);

	List<User> findByUserPhoneNumber(int userPhoneNumber);

	List<User> findByUserEmail(String userEmail);
}
