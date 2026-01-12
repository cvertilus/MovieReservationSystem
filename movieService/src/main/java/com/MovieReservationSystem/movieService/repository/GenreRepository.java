package com.MovieReservationSystem.movieService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.MovieReservationSystem.movieService.model.Genre;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {
    List<Genre> findAllByIdIn(Collection<Long> ids);
}
