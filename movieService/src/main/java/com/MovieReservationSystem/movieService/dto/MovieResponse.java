package com.MovieReservationSystem.movieService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse {
    private Long id;
    private String title;
    private String duration;
    private List<String> genreNames;
    private String imageUrl;
    private String description;
    private List<ShowtimeResponseShort> showtimes;


}
