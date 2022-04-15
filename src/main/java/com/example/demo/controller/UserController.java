package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Iterator;
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

import com.example.demo.model.Reservation;
import com.example.demo.model.User;
import com.example.demo.model.UserRepository;

@CrossOrigin(origins = "http://localhost:8081") // use for Vue
@RequestMapping("/api")
@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name) {

		try {
			List<User> users = new ArrayList<User>();

			if (name == null)
				userRepository.findAll().forEach(users::add);
			else
				userRepository.findByUserName(name).forEach(users::add);

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}

	// Before adding a new user, we need to check if the reservation is exist in the
	// reservation list.
	// haven't done yet
	@PostMapping("/users")
	public ResponseEntity<User> addOneUser(@RequestBody User user) {
		try {
			if (userRepository.findByUserName(user.getUserName()).size() > 0) {
				return new ResponseEntity<User>(HttpStatus.CHECKPOINT); // if this user has been added before, stop the
																		// process
			}
			User _user = new User(user.getUserName(), user.getUserPassword(), user.getUserPhoneNumber(),
					user.getUserAddress(), user.getUserEmail());
			if (user.getReservations() != null) {
				Iterator<Reservation> iterator = user.getReservations().iterator();
				while (iterator.hasNext()) {
					_user.addOneReservation(iterator.next());
				}
			}
			userRepository.save(_user);
			return new ResponseEntity<User>(_user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Before update the reservation, we need to check if the new reservation is
	// existed in the reservation list.
	// haven't done yet
	@PutMapping("/users/{id}")
	public ResponseEntity<User> editOneUser(@RequestBody User user, @PathVariable("id") long id) {
		Optional<User> userData = userRepository.findById(id);
		if (userData.isPresent()) {
			User _user = userData.get();
			_user.setUserName(user.getUserName());
			_user.setUserEmail(user.getUserEmail());
			_user.setUserAddress(user.getUserAddress());
			_user.setUserPassword(user.getUserPassword());

			_user.deletAllReservations();

			Iterator<Reservation> iterator = user.getReservations().iterator();
			while (iterator.hasNext()) {
				_user.addOneReservation(iterator.next());
			}
			userRepository.save(_user);
			return new ResponseEntity<User>(_user, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteOneUserById(@PathVariable("id") long id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
