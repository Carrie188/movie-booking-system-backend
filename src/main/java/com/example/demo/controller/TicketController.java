package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.example.demo.model.Cinema;
import com.example.demo.model.Show;
import com.example.demo.model.ShowRepository;
import com.example.demo.model.Ticket;
import com.example.demo.model.TicketRepository;

@CrossOrigin(origins = "http://localhost:8081") // use for Vue
@RestController
@RequestMapping("/api")
public class TicketController {

	@Autowired
	TicketRepository ticketRepository;

	@GetMapping("/tickets/{id}")
	public ResponseEntity<Ticket> getTicketById(@PathVariable("id") long id) {
		Optional<Ticket> ticketData = ticketRepository.findById(id);

		if (ticketData.isPresent()) {
			return new ResponseEntity<>(ticketData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/tickets")
	public ResponseEntity<List<Ticket>> getAllTickets() {

		try {
			List<Ticket> tickets = new ArrayList<Ticket>();

//					courseRepository.findAll().forEach(courses::add);
			List<Ticket> results = ticketRepository.findAll();
			for (Ticket t : results) {
				tickets.add(t);
			}

			return new ResponseEntity<>(tickets, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/tickets")
	public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
		try {
			Ticket newTicket = new Ticket(ticket.getPrice());
			Ticket _ticket = ticketRepository.save(newTicket);
			return new ResponseEntity<>(_ticket, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/tickets/{id}")
	public ResponseEntity<Ticket> updateTicket(@PathVariable("id") long id, @RequestBody Ticket ticket) {
		Optional<Ticket> ticketData = ticketRepository.findById(id);

		if (ticketData.isPresent()) {
			Ticket _ticket = ticketData.get();
			_ticket.setPrice(ticket.getPrice());
			_ticket.setReservation(ticket.getReservation());
			_ticket.setSeat(ticket.getSeat());
			_ticket.setShow(ticket.getShow());
			return new ResponseEntity<>(ticketRepository.save(_ticket), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/tickets/{id}")
	public ResponseEntity<HttpStatus> deleteTicket(@PathVariable("id") long id) {
		try {
			ticketRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
