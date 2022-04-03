package com.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenRpository extends JpaRepository<Screen, Long> {
	
	List<Screen> findScreenByName(String name);
	
}
