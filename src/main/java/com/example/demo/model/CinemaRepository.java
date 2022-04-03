package com.example.demo.model;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
	
	List<Cinema> findCinemaByLocation(String location);
	List<Cinema> findCinemaByName(String name);


}
