package com.codeoncewithakash.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeoncewithakash.entity.Movie;

public interface MovieRepo extends JpaRepository<Movie, Long> {

}
