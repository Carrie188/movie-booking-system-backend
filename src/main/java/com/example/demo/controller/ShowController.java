package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.Check;
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

import com.example.demo.model.Movie;
import com.example.demo.model.MovieRepository;
import com.example.demo.model.Screen;
import com.example.demo.model.ScreenRpository;
import com.example.demo.model.Show;
import com.example.demo.model.ShowRepository;

@CrossOrigin(origins = "http://localhost:8081") // use for Vue
@RestController
@RequestMapping("/api")
public class ShowController {
	
	@Autowired
	ShowRepository showRepository;
	
	
	//new 
	@Autowired
	MovieRepository movieRepoistory;
	@Autowired
	ScreenRpository screenRepository;  
	private long tempMovieID=0;
	private long tempScreenID=0;
	
	
	@GetMapping("/shows/{id}")
	public ResponseEntity<Show> getShowById(@PathVariable("id") long id) {
		Optional<Show> showData = showRepository.findById(id);

		if (showData.isPresent()) {
			return new ResponseEntity<>(showData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping("/shows")
	public ResponseEntity<List<Show>> getAllShows(
			@RequestParam(required = false) String date) {

		try {
			List<Show> shows = new ArrayList<Show>();
			if (date == null) {
//					courseRepository.findAll().forEach(courses::add);
				List<Show> results = showRepository.findAll();
				for(Show c : results) {
					shows.add(c);
				}

			} else {
				showRepository.getShowsByDate(date).forEach(shows::add);
			}
			
			return new ResponseEntity<>(shows, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	

	// New (need to add some condition)
	@PostMapping("/shows")
	public ResponseEntity<Show> createShow(@RequestBody Show show) {
		try {
			Show _show = showRepository.save(new Show(show.getDate(),show.getStartTime(),show.getEndTime()));
			System.out.println("Check tempScreenID: "+ tempScreenID);
			
			if (tempMovieID!=0) {
				Optional<Movie> movielist=movieRepoistory.findById(tempMovieID);
//				System.out.print(movielist.get().getTitle());
				Movie movie= movielist.get();
				movie.addShow(_show);
				movieRepoistory.save(movie);
			}
			if (tempScreenID!=0) {
//				System.out.println("work");
				Optional<Screen> screenList= screenRepository.findById(tempScreenID);
//				System.out.println("Screen: "+screenList.get().getName());
				Screen screen= screenList.get();
				screen.addShows(_show);
				screenRepository.save(screen);
			}
			
			return new ResponseEntity<>(_show, HttpStatus.CREATED);
		} catch (Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * This controller is just for user to pass the movie id into this class.
	 *	
	 * @param id
	 * @return
	 */
		@PostMapping("/PutMovieID")
		public ResponseEntity<Long> putMovieIdInTemp(@RequestParam (required=true) long id) {	
			try {
				tempMovieID= id ; 
				return new ResponseEntity<>(id, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		
	/**
	 * This method is just for user to pass the screen id into this class
	 * 
	 * @param id
	 * @param show
	 * @return
	 */
		@PostMapping("/PutScreenID")
		public ResponseEntity<Long> putScreenIdInTemp(@RequestParam (required=true) long id) {	
			try {
				tempScreenID= id ; 
				return new ResponseEntity<>(id, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	
	
	@PutMapping("/shows/{id}")
	public ResponseEntity<Show> updateShow(@PathVariable("id") long id, @RequestBody Show show) {
		Optional<Show> showData = showRepository.findById(id);

		if (showData.isPresent()) {
			Show _show = showData.get();
			_show.setDate(show.getDate());
			_show.setEndTime(show.getEndTime());
			_show.setStartTime(show.getStartTime());
			return new ResponseEntity<>(showRepository.save(_show), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/shows/{id}")
	public ResponseEntity<HttpStatus> deleteShow(@PathVariable("id") long id) {
		try {
			showRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
