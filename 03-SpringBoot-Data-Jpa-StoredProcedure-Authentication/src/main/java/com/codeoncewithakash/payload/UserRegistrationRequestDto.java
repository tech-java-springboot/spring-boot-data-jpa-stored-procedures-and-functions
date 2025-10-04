package com.codeoncewithakash.payload;

import java.time.LocalDate;

public record UserRegistrationRequestDto(String firstName, String lastName, Long phoneNumber, String username, String password, LocalDate dob) {

}
