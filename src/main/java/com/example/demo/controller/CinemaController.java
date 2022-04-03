package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cinema;
import com.example.demo.model.CinemaRepository;




@CrossOrigin(origins = "http://localhost:8081") // use for Vue
@RestController
@RequestMapping("/api")
public class CinemaController {

	@Autowired
	CinemaRepository cinemaRepository;

	
	
	// end point, by convention, we use a noun (not getCourses, etc.
	@GetMapping("/cinemas")
	public ResponseEntity<List<Cinema>> getAllCinemas(
			@RequestParam(required = false) String name) {

		try {
			List<Cinema> cinemas = new ArrayList<Cinema>();
			if (name == null) {
//					courseRepository.findAll().forEach(courses::add);
				List<Cinema> results = cinemaRepository.findAll();
				for(Cinema c : results) {
					cinemas.add(c);
				}

			} else {
				cinemaRepository.findCinemaByName(name).forEach(cinemas::add);
			}
			
			return new ResponseEntity<>(cinemas, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	
	@GetMapping("/cinemas/{id}")
	public ResponseEntity<Cinema> getCinemaById(@PathVariable("id") long id) {
		Optional<Cinema> cinemaData = cinemaRepository.findById(id);

		if (cinemaData.isPresent()) {
			return new ResponseEntity<>(cinemaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}
