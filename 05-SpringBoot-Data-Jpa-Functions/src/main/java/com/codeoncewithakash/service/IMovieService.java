package com.codeoncewithakash.service;

import com.codeoncewithakash.payload.MovieRequestDto;
import com.codeoncewithakash.payload.MovieResponseDto;
import com.codeoncewithakash.payload.MoviesByRatingResponse;

public interface IMovieService {
	public MovieResponseDto registerMovie(MovieRequestDto movieRequestDto);
	public MoviesByRatingResponse getMoviesByRating(Double startRating, Double endRating);
}
