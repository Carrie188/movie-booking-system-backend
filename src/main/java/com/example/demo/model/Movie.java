package com.example.demo.model;

import java.util.Date;
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
@Table(name = "movies")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;
	
	@Column(name = "durationMins")
	private int durationMins;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "releaseDate")
	private Date releaseDate;
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
	private Set<Show> shows = new HashSet<>();
	


	public Movie() {
		
	}


	public Movie(String title, String description, int durationMins, String category, Date releaseDate) {
		super();
		this.title = title;
		this.description = description;
		this.durationMins = durationMins;
		this.category = category;
		this.releaseDate = releaseDate;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getDurationMins() {
		return durationMins;
	}


	public void setDurationMins(int durationMins) {
		this.durationMins = durationMins;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public Date getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}


	public Set<Show> getShows() {
		return shows;
	}


	public void setShows(Set<Show> shows) {
		this.shows = shows;
	}

	public void addShow(Show show) {
		this.shows.add(show);
		show.setMovie(this);
		
	}

	
	
	
}
