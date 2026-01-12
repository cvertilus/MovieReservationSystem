package com.MovieReservationSystem.movieService.service;

import com.MovieReservationSystem.movieService.dto.DtoMapper;
import com.MovieReservationSystem.movieService.dto.GenreRequest;
import com.MovieReservationSystem.movieService.dto.GenreResponse;
import com.MovieReservationSystem.movieService.repository.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.MovieReservationSystem.movieService.model.Genre;
import com.MovieReservationSystem.movieService.model.Movie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.MovieReservationSystem.movieService.repository.GenreRepository;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;
    private final DtoMapper mapper;
    private final MovieRepository movieRepository;

    public List<GenreResponse> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return  genres.stream().map(mapper::toGenreResponse).toList();
    }

    public List<Genre> getAllGenreAtOonce(Collection<Long> ids){
        return genreRepository.findAllByIdIn(ids);
    }

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No genre found with id " + id));
    }

    public GenreResponse getGenreResponseById(Long id) {
        Genre genre = getGenreById(id);
        return mapper.toGenreResponse(genre);
    }

    public GenreResponse createGenre(GenreRequest genreRequest) {
      Genre genre = genreRepository.save(mapper.toGenre(genreRequest));
      return mapper.toGenreResponse(genre);
    }

    @Transactional
    public void deleteGenre(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No genre found with id " + id));
        List<Movie> movies = movieRepository.findByGenresContaining(genre);
        for (Movie movie : movies) {
            movie.getGenres().remove(genre);
        }
        genreRepository.delete(genre);

    }

}
