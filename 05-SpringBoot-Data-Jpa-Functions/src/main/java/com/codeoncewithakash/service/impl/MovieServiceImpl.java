package com.codeoncewithakash.service.impl;


import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.dialect.OracleTypes;
import org.springframework.stereotype.Service;

import com.codeoncewithakash.entity.Movie;
import com.codeoncewithakash.payload.MovieRequestDto;
import com.codeoncewithakash.payload.MovieResponseDto;
import com.codeoncewithakash.payload.MoviesByRatingResponse;
import com.codeoncewithakash.repo.MovieRepo;
import com.codeoncewithakash.service.IMovieService;

import jakarta.persistence.EntityManager;

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
		return new MovieResponseDto(registeredMovie.getLanguage(), registeredMovie.getMovieName(), registeredMovie.getRating(), registeredMovie.getReleaseYear());
	}

	@Override
	public MoviesByRatingResponse getMoviesByRating(Double startRating, Double endRating) {
		//1. Unwrap Session object from EntityManager object
		Session session = entityManager.unwrap(Session.class);
		
		
		//2. Work with ReturningWork<T> callback interface based callback method.
		return session.doReturningWork(con ->{
			
			//2.1 Write CallbackStatement based logic to call PL/SQL function
			CallableStatement cs = con.prepareCall("{?=call FX_GET_MOVIES_BY_RATING(?,?,?)}");
			
			//2.2 Register OUT parameter as return type for the position #1 and out parameter for the position #4
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.registerOutParameter(4, Types.INTEGER);
			
			//2.3 Set values to IN parameters
			cs.setDouble(2, startRating);
			cs.setDouble(3, endRating);
			
			//2.4 call PL/SQL Function
			cs.execute();
			
			//2.5 Gather results from output parameters
			ResultSet rs = (ResultSet)cs.getObject(1);
			int count = cs.getInt(4);
			
			//2.6 Process and set result set values to DTO object
			List<MovieResponseDto> list = new ArrayList<>();
			while(rs.next()) {
				list.add(new MovieResponseDto(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4)));
			}
			
			return new MoviesByRatingResponse(count, list);
		});
	}

}
