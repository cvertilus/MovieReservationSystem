package com.MovieReservationSystem.movieService.dto;

import com.MovieReservationSystem.movieService.model.Auditorium;
import com.MovieReservationSystem.movieService.model.Genre;
import com.MovieReservationSystem.movieService.model.Movie;
import com.MovieReservationSystem.movieService.model.Showtime;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DtoMapper {
    public Movie toMovie(MovieRequest movieRequest, Set<Genre> genres) {
        return Movie.builder()
                .title(movieRequest.getTitle())
                .description(movieRequest.getDescription())
                .duration(movieRequest.getDuration())
                .imageUrl(movieRequest.getImageUrl())
                .genres(genres)
                .build();
    }

    public MovieResponse toMovieResponse(Movie movie) {
        List<String> genres = movie.getGenres().stream().map(Genre::getName).toList();
        List<ShowtimeResponseShort> showtimes = movie.getShowtimes().stream().map(this::toShowtimeResponseShort).toList();
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .duration(movie.getDuration())
                .imageUrl(movie.getImageUrl())
                .genreNames(genres)
                .showtimes(showtimes)
                .build();
    }

    public GenreResponse toGenreResponse(Genre genre) {
        return GenreResponse.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();

    }

    public ShowtimeResponse toShowtimeResponse(Showtime showtime) {
        Movie movie = showtime.getMovie();
        return ShowtimeResponse.builder()
                .id(showtime.getId())
                .startTime(showtime.getStartTime())
                .endTime(showtime.getEndTime())
                .auditorium(showtime.getAuditorium().getName())
                .movieTitle(movie!= null ? movie.getTitle() : "Sin pelicula asignada")
                .build();

    }

    public Showtime toShowtime(ShowTimeRequest showtimeRequest, Movie movie) {
             //agregar auditorium PARA SOLUCIONAR DESPUES
        return Showtime.builder()
                .startTime(showtimeRequest.getStartTime())
                .endTime(showtimeRequest.getEndTime())
                .auditorium(null)//agregar auditorium PARA SOLUCIONAR DESPUES
                .movie(movie)
                .build();
    }

    public ShowtimeResponseShort toShowtimeResponseShort(Showtime showtime) {
        return ShowtimeResponseShort.builder()
                .startTime(showtime.getStartTime())
                .endTime(showtime.getEndTime())
                .auditorium(showtime.getAuditorium().getName())
                .build();
    }

    public Genre toGenre(GenreRequest genreRequest) {
        return Genre.builder()
                .name(genreRequest.getName())
                .build();
    }


    public Auditorium toAuditorium(AuditoriumRequest auditoriumRequest) {
        return Auditorium.builder()
                .name(auditoriumRequest.getName())
                .capacity(auditoriumRequest.getCapacity())
                .build();
    }

    public AuditoriumResponse toAuditoriumResponse(Auditorium auditorium) {
        return AuditoriumResponse.builder()
                .id(auditorium.getId())
                .name(auditorium.getName())
                .capacity(auditorium.getCapacity())
                .build();
    }


}
