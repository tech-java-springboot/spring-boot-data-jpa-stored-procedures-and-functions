package com.codeoncewithakash.entity;

import java.io.Serializable;
import java.time.LocalDate;

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
@Table(name = "SP_USER_INFO")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen1", sequenceName = "SP_USER_INFO_SEQ", initialValue = 100, allocationSize = 1)
	@Column(name = "USER_ID")
	private Long id;
	
	@NonNull
	@Column(name = "FIRST_NAME", length = 100)
	private String firstName;
	
	@NonNull
	@Column(name = "LAST_NAME", length = 100)
	private String lastName;
	
	@NonNull
	@EqualsAndHashCode.Include
	@Column(name = "MOBILE")
	private Long phoneNumber;
	
	@NonNull
	@EqualsAndHashCode.Include
	@Column(name = "USER_NAME", length = 100, nullable = false)
	private String username;
	
	@NonNull
	@EqualsAndHashCode.Include
	@Column(name = "PASSW", length = 100, nullable = false)
	private String password;
	
	@NonNull
	@Column(name = "DOB")
	private LocalDate dob;
}
