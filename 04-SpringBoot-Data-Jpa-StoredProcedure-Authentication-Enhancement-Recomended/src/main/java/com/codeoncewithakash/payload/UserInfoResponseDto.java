package com.codeoncewithakash.payload;

import java.time.LocalDate;

public record UserInfoResponseDto(Long id, String firstName, String lastName, Long phoneNumber, String username, LocalDate dob) {

}
