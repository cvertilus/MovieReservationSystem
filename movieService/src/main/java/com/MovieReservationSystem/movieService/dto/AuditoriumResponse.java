package com.MovieReservationSystem.movieService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
public class AuditoriumResponse {
    private Long id;
    private String name;
    private int capacity;
    private List<ShowtimeResponseAuditorium> Showtime;
}
