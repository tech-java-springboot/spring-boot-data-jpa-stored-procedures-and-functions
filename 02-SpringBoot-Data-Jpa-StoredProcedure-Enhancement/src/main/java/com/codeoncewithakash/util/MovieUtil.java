package com.codeoncewithakash.util;

import com.codeoncewithakash.entity.Movie;
import com.codeoncewithakash.payload.MovieResponseDto;

public class MovieUtil {
	
	private MovieUtil() {
		
	}
	
	public static MovieResponseDto mapToResponseDto(Movie movie) {
		return new MovieResponseDto(movie.getId(), movie.getMovieName(), movie.getReleaseYear(), movie.getRating(), movie.getLanguage());
	}
}
