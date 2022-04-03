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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

	@Column(name = "userName")
	private String userName;

	@Column(name = "userPassword")
	private String userPassword;

	@Column(name = "userPhoneNumber")
	private String userPhoneNumber;

	@Column(name = "userAddress")
	private String userAddress;

	@Column(name = "userEmail")
	private String userEmail;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Reservation> reservation = new HashSet<>();

	public User() {

	}

	public User(String userName, String userPassword, String userPhoneNumber, String userAddress, String userEmail) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.userPhoneNumber = userPhoneNumber;
		this.userAddress = userAddress;
		this.userEmail = userEmail;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Set<Reservation> getReservations() {
		return reservation;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservation = reservations;
	}

	public void addOneReservation(Reservation reservations) {
		this.reservation.add(reservations);
		reservations.setUser(this);
	}

	public void deleteOneReservation(Reservation reservation) {
		this.reservation.remove(reservation);
	}

	public void deletAllReservations() {
		this.reservation.clear();
	}
}
