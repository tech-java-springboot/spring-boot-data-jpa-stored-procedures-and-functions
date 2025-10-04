package com.codeoncewithakash.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codeoncewithakash.constant.MovieConstant;
import com.codeoncewithakash.entity.Movie;
import com.codeoncewithakash.payload.MovieRequestDto;
import com.codeoncewithakash.payload.MovieResponseDto;
import com.codeoncewithakash.repo.MovieRepo;
import com.codeoncewithakash.service.IMovieService;
import com.codeoncewithakash.util.MovieUtil;

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
	  SELECT MOVIE_ID, MOVIE_NAME, RELEASE_YEAR, RATING, LANGUAGE FROM MOVIE_TAB WHERE RELEASE_YEAR>=STARTYEAR AND RELEASE_YEAR<=ENDYEAR;
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
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(MovieConstant.SP_GET_MOVIES_BY_YEAR_RANGE, Movie.class);
		
		//2. Register parameters of PL/SQL procedure with Java types.
		query.registerStoredProcedureParameter("startYear", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("endYear", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("outCursor", void.class, ParameterMode.REF_CURSOR);
		
		//3. Set values to IN parameters.
		query.setParameter("startYear", startYear);
		query.setParameter("endYear", endYear);
		
		//4. Call PL/SQL Procedure and get the results.
		@SuppressWarnings("unchecked")
		List<Movie> movies = query.getResultList();
		return movies.stream().map(MovieUtil::mapToResponseDto).toList();
	}

}
