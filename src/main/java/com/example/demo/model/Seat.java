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
@Table(name = "seats")
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "seatNumber")
	private String seatNumber;

//	@Column(name = "seatRow")
//	private int seatRow;
//
//	@Column(name = "seatColumn")
//	private int seatColumn;

	@Column(name = "isAvailable")
	private boolean isAvailable;

//	@JsonIgnore
//	@OneToMany(mappedBy = "seat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private Set<Ticket> tickets = new HashSet<>() ;

//	
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
//	@JoinColumn(name = "screen_id", nullable = true)
//	@JsonIgnore
//	private Screen screen;

	public Seat() {

	}
	
	public Seat(String seatNumber, boolean isAvailable) {
		super();
		this.seatNumber = seatNumber;
		this.isAvailable = isAvailable;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
//
//	public Set<Ticket> getTickets() {
//		return tickets;
//	}
//
//	public void setTickets(Set<Ticket> tickets) {
//		this.tickets = tickets;
//	}
//	
//	public void addTicket(Ticket ticket) {
//		this.tickets.add(ticket);
////		ticket.setSeat(this);
//	}
//	
//	public void removeTicket(Ticket ticket) {
//		this.tickets.remove(ticket);
////		ticket.setSeat(null);
//	}
//
//	public Screen getScreen() {
//		return screen;
//	}
//
//	public void setScreen(Screen screen) {
//		this.screen = screen;
//	}



}
