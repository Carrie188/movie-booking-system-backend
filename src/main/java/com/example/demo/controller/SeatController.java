package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Seat;
import com.example.demo.model.SeatRepository;
import com.example.demo.model.Show;
import com.example.demo.model.ShowRepository;

@CrossOrigin(origins = "http://localhost:8081") // use for Vue
@RestController
@RequestMapping("/api")
public class SeatController {
	
	@Autowired
	SeatRepository seatRepository;
	
	@PostMapping("/seats")
	public ResponseEntity<Seat> createShow(@RequestBody Seat seat) {
		try {
			Seat _seat = seatRepository.save(new Seat(seat.getSeatNumber(),seat.getSeatRow(),seat.getSeatColumn(),seat.isAvailable()));
			
			return new ResponseEntity<>(_seat, HttpStatus.CREATED);
		} catch (Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
