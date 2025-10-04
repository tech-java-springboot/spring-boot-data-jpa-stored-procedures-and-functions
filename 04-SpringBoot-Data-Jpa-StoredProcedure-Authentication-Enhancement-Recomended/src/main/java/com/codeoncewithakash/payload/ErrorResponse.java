package com.codeoncewithakash.payload;

import java.time.LocalDateTime;

public record ErrorResponse(String message, LocalDateTime timestamp) {

}
