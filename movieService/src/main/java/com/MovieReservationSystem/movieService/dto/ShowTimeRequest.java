package com.MovieReservationSystem.movieService.dto;

import com.MovieReservationSystem.movieService.model.Auditorium;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowTimeRequest {
    @NotNull(message = "Start time cannot be null")
    @Future(message = "Start time must be in the future")
    private LocalDateTime startTime;

    @NotNull(message = "End time cannot be null")
    private LocalDateTime endTime;

    @NotNull(message = "Auditorium cannot be null")
    private Long auditoriumId;

}
