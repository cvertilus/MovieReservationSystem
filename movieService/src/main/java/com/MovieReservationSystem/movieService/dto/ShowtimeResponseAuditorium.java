package com.MovieReservationSystem.movieService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ShowtimeResponseAuditorium {
    private LocalDateTime startTime;
    private LocalDateTime EndTime;
    private Long idShowtime;
}
