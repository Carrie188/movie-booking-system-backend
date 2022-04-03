package com.example.demo;

import java.util.Date;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Cinema;
import com.example.demo.model.CinemaRepository;
import com.example.demo.model.Movie;
import com.example.demo.model.MovieRepository;
import com.example.demo.model.Reservation;
import com.example.demo.model.ReservationRepository;
import com.example.demo.model.Screen;
import com.example.demo.model.ScreenRpository;
import com.example.demo.model.Seat;
import com.example.demo.model.SeatRepository;
import com.example.demo.model.Show;
import com.example.demo.model.ShowRepository;
import com.example.demo.model.TicketRepository;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.model.UserRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	ApplicationRunner init(ReservationRepository reservationRepository, UserRepository userRepository,
			CinemaRepository cinemaRepository, ScreenRpository screenRepository, ShowRepository showRepository,
			MovieRepository movieRepository, SeatRepository seatRepository, TicketRepository ticketRepository) {
		return args -> {

			Cinema[] cinemas = { 
					new Cinema("Central Theatre", "New Westminster"),
					new Cinema("Royal Cinema", "Burnaby"), 
					new Cinema("Vancouver Theatre", "Vancouver") };

			Screen[] screens = { new Screen("Screen A", 3), new Screen("Screen B", 5), new Screen("Screen C", 7),
					new Screen("Screen D", 4), new Screen("Screen F", 8) };
			Seat[] seats = { new Seat(1, 01, 01, true), new Seat(2, 01, 02, true), new Seat(3, 01, 03, true) };

			Show[] shows = { new Show("12-2-2022", "12:00:00", "15:00:00"),
					new Show("12-2-2022", "16:00:00", "19:00:00"), new Show("12-2-2022", "19:30:00", "22:00:00") };

			Date dateForNow = new Date();

			Movie[] movies = { new Movie("Movie 1", "description1", 300, "category1", dateForNow),
					new Movie("Movie 2", "description1", 600, "category2", dateForNow) };

			// add tickets,reservations,user
			Ticket[] tickets = { new Ticket(19.0), new Ticket(25.0), new Ticket(35.0), new Ticket(15.0) };
			Reservation[] reservations = { new Reservation(), new Reservation(), new Reservation() };
			User[] userList = { new User("Jack", "A123456", "7783222333", "address1", "Jack@gmail"),
					new User("An", "B123456", "7783232335", "address2", "An@gmail") };

			reservations[0].addTickets(tickets[0]);
			reservations[1].addTickets(tickets[1]);
			reservations[2].addTickets(tickets[2]);
			reservations[2].addTickets(tickets[3]);

			userList[0].addOneReservation(reservations[0]);
			userList[1].addOneReservation(reservations[1]);
			userList[1].addOneReservation(reservations[2]);

			tickets[0].setSeat(seats[0]);
			tickets[1].setSeat(seats[1]);
			tickets[2].setSeat(seats[2]);
			
			
			
			shows[0].addTicket(tickets[0]);
			shows[0].addTicket(tickets[1]);
			shows[0].addTicket(tickets[2]);
			
			shows[1].addTicket(tickets[0]);
			shows[1].addTicket(tickets[1]);
			shows[1].addTicket(tickets[2]);
			
			shows[2].addTicket(tickets[0]);
			shows[2].addTicket(tickets[1]);
			shows[2].addTicket(tickets[2]);

			// add show to screen
			screens[0].addShows(shows[0]);
			screens[1].addShows(shows[1]);
			screens[2].addShows(shows[2]);
			
			// add seats to screen
			screens[0].addSeat(seats[0]);
			screens[0].addSeat(seats[1]);
			screens[0].addSeat(seats[2]);

			// add screens into cinemas
			cinemas[0].addScreen(screens[0]);
			cinemas[0].addScreen(screens[1]);
			cinemas[0].addScreen(screens[2]);
			cinemas[1].addScreen(screens[3]);
			cinemas[1].addScreen(screens[4]);

			// add movie into shows
			movies[0].addShow(shows[0]);
			movies[0].addShow(shows[1]);
			movies[1].addShow(shows[2]);

			for (int i = 0; i < cinemas.length; i++) {
				cinemaRepository.save(cinemas[i]);
			}

			for (int i = 0; i < seats.length; i++) {
				seatRepository.save(seats[i]);
			}

			for (int i = 0; i < movies.length; i++) {
				movieRepository.save(movies[i]);
			}

			for (int i = 0; i < shows.length; i++) {
				showRepository.save(shows[i]);
			}

			for (int i = 0; i < screens.length; i++) {
				screenRepository.save(screens[i]);
			}

			for (int i = 0; i < userList.length; i++) {
				userRepository.save(userList[i]);
			}

			// print info for debug
			cinemaRepository.findAll().forEach(System.out::println);
			movieRepository.findAll().forEach(System.out::println);

		};

	}

}
