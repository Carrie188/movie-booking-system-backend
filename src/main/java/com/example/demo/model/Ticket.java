package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tickets")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "price")
	private double price;

//	@Column(name = "cost")
//	private double cost;
//
//	@Column(name = "screens")
//	private String screens;
//
//	@Column(name = "loaction")
//	private String location;

//	@JsonIgnore
//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "seat_id", nullable = false)
//	private Seat seat;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "show_id", nullable = true)
	private Show show;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "reservation_id", nullable = true)
	private Reservation reservation;

	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "seat_id", nullable = true)
	private Seat seat;

	public Ticket() {

	}

	public Ticket(double price) {
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	

//	public Seat getSeat() {
//		return seat;
//	}
//
//	public void setSeat(Seat seat) {
//		this.seat = seat;
//	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	
	
	
//	public Set<Seat> getSeats() {
//		return seats;
//	}
//
//	public void setSeats(Set<Seat> seats) {
//		this.seats = seats;
//	}
//
//	public void addSeat(Seat seat) {
//		this.seats.add(seat);
//		seat.setTicket(this);
//	}

//	public double getCost() {
//		return cost;
//	}
//
//	public void setCost(double cost) {
//		this.cost = cost;
//	}
//
//	public String getLocation() {
//		return location;
//	}
//
//	public void setLocation(String location) {
//		this.location = location;
//	}

}
