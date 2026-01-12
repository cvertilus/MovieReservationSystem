package com.MovieReservationSystem.movieService.repository;

import com.MovieReservationSystem.movieService.model.Genre;
import com.MovieReservationSystem.movieService.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByGenresContaining(Genre genre);

    @Query ("SELECT DISTINCT m FROM Movie m " +
            "LEFT JOIN FETCH m.genres " +
            "LEFT JOIN FETCH m.showtimes ")
    List<Movie> findAllWithDetail();
}
