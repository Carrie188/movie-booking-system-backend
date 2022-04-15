package com.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	List<Movie> findMovieByTitle(String title);
	List<Movie> findMovieByCategory(String category);

}
