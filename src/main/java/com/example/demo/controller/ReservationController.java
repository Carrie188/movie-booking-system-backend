package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Reservation;
import com.example.demo.model.ReservationRepository;
import com.example.demo.model.Seat;
import com.example.demo.model.Show;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.model.UserRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
@RestController
public class ReservationController {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	UserRepository userRepository;
	
	

	@GetMapping("/reservations")
	public ResponseEntity<List<Reservation>> getAllReservations(@RequestParam(required = false) String name) {
		if (reservationRepository.findAll() != null) {
			return new ResponseEntity<List<Reservation>>(reservationRepository.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/reservations/{id}")
	public ResponseEntity<Reservation> getReservation(@PathVariable("id") long id) {
		Optional<Reservation> reservationData = reservationRepository.findById(id);

		if (reservationData.isPresent()) {
			Reservation _reservation = reservationData.get();
			
			return new ResponseEntity<>(_reservation, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

	@PostMapping("/reservations")
	public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
		try {
			Reservation newReservation = new Reservation(reservation.getNumberOfTickets(), reservation.getTotalCost());
			Reservation _reservation = reservationRepository.save(newReservation);
			return new ResponseEntity<Reservation>(_reservation, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	

	
	

	// add tickets to the reservation	
	
	@PutMapping("/reservations/{id}/setuser")
	public ResponseEntity<Reservation> addTickets(@PathVariable("id") long id,  @RequestBody User user) {
		Optional<Reservation> reservationData = reservationRepository.findById(id);
		Optional<User> userData = userRepository.findById(user.getUserId());
		User _user = new User(user.getUserName(),user.getUserPassword(),user.getUserPhoneNumber(),user.getUserAddress(),user.getUserEmail());
		if (reservationData.isPresent()) {
			Reservation _reservation = reservationData.get();
			_reservation.setUser(_user);
			System.out.println(_reservation.getUser());
			return new ResponseEntity<>(reservationRepository.save(_reservation), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
	
	
	@PutMapping("/reservations/{id}/setticket")
	public ResponseEntity<Reservation> addTickets(@PathVariable("id") long id,  @RequestBody Ticket ticket) {
		Optional<Reservation> reservationData = reservationRepository.findById(id);

		if (reservationData.isPresent()) {
			Reservation _reservation = reservationData.get();
			_reservation.addTickets(ticket);
			
			return new ResponseEntity<>(reservationRepository.save(_reservation), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
	
	
	
	
//	@PutMapping("/reservation/{id}")
//	public ResponseEntity<Reservation> updateReservation(@PathVariable("id") long id,
//			@RequestBody Reservation reservation) {
//		Optional<Reservation> reservationData = reservationRepository.findById(id);
//
//		if (reservationData.isPresent()) {
//			Reservation _reservation = reservationData.get();
//			_reservation.setNumberOfTickets(reservation.getNumberOfTickets());
//			_reservation.setTotalCost(reservation.getTotalCost());
//			_reservation.setUser(reservation.getUser());
//			_reservation.setTickets(reservation.getTickets());
//			return new ResponseEntity<>(reservationRepository.save(_reservation), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}

	@DeleteMapping("/reservation/{id}")
	public ResponseEntity<HttpStatus> deleteReservation(@PathVariable("id") long id) {
		try {
			reservationRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@PostMapping("/reservation")
//	public ResponseEntity<Reservation> addOneReservation(@RequestBody Reservation reservation) {
//		try {
//			Reservation _reservation = new Reservation(reservation.getUser(), reservation.getTotalCost(),
//					reservation.getSessionId(), reservation.getTicketNumber());
//			return new ResponseEntity<Reservation>(reservationRepository.save(_reservation), HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<Reservation>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@PutMapping("/reservation/{id}")
//	public ResponseEntity<Reservation>editReservation(@PathVariable("id") long id, @RequestBody Reservation reservation){
//		Optional<Reservation> reservationList=reservationRepository.findById(id);
//		if ( reservationList.isPresent()) {
//			Reservation _reservation= reservationList.get();
//			_reservation.setSessionId(reservation.getSessionId());
//			_reservation.setTicketNumber(reservation.getTicketNumber());
//			_reservation.setTotalCost(reservation.getTicketNumber());
//			_reservation.setUser(reservation.getUser());
//			reservationRepository.save( _reservation);
//			return new ResponseEntity<Reservation>(_reservation,HttpStatus.OK);
//		}  else {
//			return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
//		}
//	}
}
