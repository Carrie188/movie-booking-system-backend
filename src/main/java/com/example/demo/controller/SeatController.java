package com.example.demo.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Screen;
import com.example.demo.model.ScreenRpository;
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
	public ResponseEntity<Seat> createSeats(@RequestBody Seat seat) {
		try {
			Seat _seat = seatRepository.save(new Seat(seat.getSeatNumber(),seat.isAvailable()));
			
			return new ResponseEntity<>(_seat, HttpStatus.CREATED);
		} catch (Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@PutMapping("/seats/{id}")
//	public ResponseEntity<Seat> setShowScreen(@PathVariable("id") Long sid, @RequestBody Screen screen) {
//		
//			Optional<Seat> seatData = seatRepository.findById(sid);
//			seatData.set
//		
//			if (seatData.isPresent()) {
//				return new ResponseEntity<>(seatData.get(), HttpStatus.OK);
//			} else {
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			}
//	}

}
