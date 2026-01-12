package com.MovieReservationSystem.movieService.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {


    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotNull(message = "Duration cannot be null")
    @NotBlank(message = "Duration cannot be blank")
    @Pattern(regexp = "^[1-9][0-9]*$", message = "Duration must be a positive integer in minutes")
    private String duration;

    @NotNull(message = "Description cannot be null")
    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "Image URL cannot be null")
    @NotBlank(message = "Image URL cannot be blank")
    private String imageUrl;

    @NotNull(message = "Genre IDs cannot be null")
    @NotEmpty(message = "Genre IDs cannot be empty")
    private Collection<@Positive(message = "ID need to be a positive number") @NotNull Long> genreIds;
}
