package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.Screen;
import com.example.demo.model.ScreenRpository;



@CrossOrigin(origins = "http://localhost:8081") // use for Vue
@RestController
@RequestMapping("/api")
public class ScreenController {
	@Autowired
	ScreenRpository screenRepository;
	
	
	@GetMapping("/screens/{id}")
	public ResponseEntity<Screen> getCinemaById(@PathVariable("id") long id) {
		Optional<Screen> screenData = screenRepository.findById(id);

		if (screenData.isPresent()) {
			return new ResponseEntity<>(screenData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	// new 
	@GetMapping("/screens")
	public ResponseEntity<List<Screen>> getCinemas() {
		try {
			return new ResponseEntity<>(screenRepository.findAll(), HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
