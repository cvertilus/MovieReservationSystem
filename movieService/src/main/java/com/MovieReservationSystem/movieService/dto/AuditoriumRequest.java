package com.MovieReservationSystem.movieService.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor

public class AuditoriumRequest {
    @NotNull(message=" Name cannot be null")
   @NotBlank(message=" Name cannot be blank")
    private String name;

    @NotNull(message=" Capacity cannot be null")
    @Positive(message=" Capacity must be a positive number")
    @Min(message=" Capacity must be at least 1", value=1)
    private int capacity;

}
