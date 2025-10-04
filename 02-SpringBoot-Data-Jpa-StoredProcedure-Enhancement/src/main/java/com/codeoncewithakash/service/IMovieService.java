package com.codeoncewithakash.service;

import java.util.List;

import com.codeoncewithakash.payload.MovieRequestDto;
import com.codeoncewithakash.payload.MovieResponseDto;

public interface IMovieService {
	public MovieResponseDto registerMovie(MovieRequestDto movieRequestDto);
	public List<MovieResponseDto> getMoviesByReleaseYearRange(String startYear, String endYear);
}
