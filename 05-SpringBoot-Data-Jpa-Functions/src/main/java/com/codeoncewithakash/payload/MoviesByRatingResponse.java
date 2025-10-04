package com.codeoncewithakash.payload;

import java.util.List;

public record MoviesByRatingResponse(int count, List<MovieResponseDto> movieResponseDto) {

}
