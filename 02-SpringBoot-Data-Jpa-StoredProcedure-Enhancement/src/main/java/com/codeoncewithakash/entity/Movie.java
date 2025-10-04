package com.codeoncewithakash.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MOVIE_TAB")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen1", sequenceName = "MOVIE_SEQ", initialValue = 100, allocationSize = 1)
	@Column(name = "MOVIE_ID")
	private Long id;
	
	@NonNull
	@EqualsAndHashCode.Include
	@Column(name = "MOVIE_NAME", length = 100)
	private String movieName;
	
	@NonNull
	@Column(name = "RELEASE_YEAR", length = 100)
	private String releaseYear;
	
	@NonNull
	@Column(name = "RATING")
	private Double rating;
	
	@Column(name = "LANGUAGE", length = 100)
	private String language;
}
