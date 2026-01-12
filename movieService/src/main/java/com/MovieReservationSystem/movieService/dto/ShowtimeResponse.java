package com.MovieReservationSystem.movieService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowtimeResponse {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String auditorium;
    private String movieTitle;
}
