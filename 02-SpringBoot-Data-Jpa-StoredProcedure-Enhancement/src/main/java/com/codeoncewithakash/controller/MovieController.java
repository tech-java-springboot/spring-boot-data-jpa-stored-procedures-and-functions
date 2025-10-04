package com.codeoncewithakash.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeoncewithakash.payload.MovieRequestDto;
import com.codeoncewithakash.payload.MovieResponseDto;
import com.codeoncewithakash.service.IMovieService;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {
	
	private IMovieService movieService;
	
	public MovieController(IMovieService movieService) {
		this.movieService = movieService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<MovieResponseDto> registerMovie(@RequestBody MovieRequestDto movieRequestDto) {
		MovieResponseDto movieResponseDto = movieService.registerMovie(movieRequestDto);
		return new ResponseEntity<>(movieResponseDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/get/movies/year/{startYear}/{endYear}")
	public ResponseEntity<List<MovieResponseDto>> getMoviesByReleaseYearRange(@PathVariable(name = "startYear") String startYear, @PathVariable(name = "endYear") String endYear){
		List<MovieResponseDto> moviesByReleaseYearRange = movieService.getMoviesByReleaseYearRange(startYear, endYear);
		return ResponseEntity.ok(moviesByReleaseYearRange);
	}
}
