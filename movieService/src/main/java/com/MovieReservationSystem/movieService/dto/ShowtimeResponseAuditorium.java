package com.MovieReservationSystem.movieService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ShowtimeResponseAuditorium {
    private LocalDateTime StartTime;
    private LocalDateTime EndTime;
    private String MovieTitle;
}
