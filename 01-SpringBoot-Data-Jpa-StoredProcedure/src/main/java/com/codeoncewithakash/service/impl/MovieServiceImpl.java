package com.codeoncewithakash.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codeoncewithakash.entity.Movie;
import com.codeoncewithakash.payload.MovieRequestDto;
import com.codeoncewithakash.payload.MovieResponseDto;
import com.codeoncewithakash.repo.MovieRepo;
import com.codeoncewithakash.service.IMovieService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

/**
 * Stored Procedure To Get Movies Details Based On The Year Range.

	CREATE OR REPLACE PROCEDURE P_GET_MOVIES_BY_YEAR_RANGE 
	(
	  STARTYEAR IN VARCHAR2 
	, ENDYEAR IN VARCHAR2 
	, DETAILS OUT SYS_REFCURSOR 
	) AS 
	BEGIN
	  open details for
	  SELECT movie_id, movie_name, release_year, rating, language FROM movie_tab WHERE release_year>=STARTYEAR AND release_year<=ENDYEAR;
	END P_GET_MOVIES_BY_YEAR_RANGE;
*/

@Service
public class MovieServiceImpl implements IMovieService {

	private MovieRepo movieRepo;
	
	private EntityManager entityManager;
	
	public MovieServiceImpl(MovieRepo movieRepo, EntityManager entityManager) {
		this.movieRepo = movieRepo;
		this.entityManager = entityManager;
	}
	
	@Override
	public MovieResponseDto registerMovie(MovieRequestDto movieRequestDto) {
		Movie movie = Movie.builder()
		.movieName(movieRequestDto.movieName())
		.releaseYear(movieRequestDto.releaseYear())
		.rating(movieRequestDto.rating())
		.language(movieRequestDto.language())
		.build();
		
		Movie registeredMovie = movieRepo.save(movie);
		return new MovieResponseDto(registeredMovie.getId(), registeredMovie.getMovieName(), registeredMovie.getReleaseYear(), registeredMovie.getRating(), registeredMovie.getLanguage());
	}
	
	@Override
	public List<MovieResponseDto> getMoviesByReleaseYearRange(String startYear, String endYear) {
		
		//1. Create StoredProcedureQuery object representing the given PL/SQL procedure.
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("P_GET_MOVIES_BY_YEAR_RANGE", Movie.class);
		
		//2. Register parameters of PL/SQL procedure with Java types.
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(3, void.class, ParameterMode.REF_CURSOR);
		
		//3. Set values to IN parameters.
		query.setParameter(1, startYear);
		query.setParameter(2, endYear);
		
		//4. Call PL/SQL Procedure and get the results.
		@SuppressWarnings("unchecked")
		List<Movie> movies = query.getResultList();
		return movies.stream().map(movie -> new MovieResponseDto(movie.getId(), movie.getMovieName(), movie.getReleaseYear(), movie.getRating(), movie.getLanguage())).toList();
	}

}
