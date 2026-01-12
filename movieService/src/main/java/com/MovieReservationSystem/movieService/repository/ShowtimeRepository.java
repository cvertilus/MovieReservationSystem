package com.MovieReservationSystem.movieService.repository;

import com.MovieReservationSystem.movieService.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime,Long> {
}
