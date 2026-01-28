package com.MovieReservationSystem.movieService.repository;

import com.MovieReservationSystem.movieService.model.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {
}
