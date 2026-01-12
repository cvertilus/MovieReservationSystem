package com.MovieReservationSystem.movieService.service;

import com.MovieReservationSystem.movieService.dto.DtoMapper;
import com.MovieReservationSystem.movieService.dto.MovieRequest;
import com.MovieReservationSystem.movieService.dto.MovieResponse;
import com.MovieReservationSystem.movieService.repository.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.MovieReservationSystem.movieService.model.Genre;
import com.MovieReservationSystem.movieService.model.Movie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.MovieReservationSystem.movieService.repository.MovieRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final DtoMapper mapper;
    private final GenreRepository genreRepository;

    public List<Movie> getAllMoviesByGenre(Genre genre) {
        return movieRepository.findByGenresContaining(genre);
    }

    public Movie getMovieById(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new EntityNotFoundException("No movie found with id " + movieId));
    }

    public MovieResponse getMovieResponseById(Long movieId) {
        Movie movie = getMovieById(movieId);
        return mapper.toMovieResponse(movie);
    }

    public List<MovieResponse> getAllMovies() {
        List<Movie> movies = movieRepository.findAllWithDetail();
        return movies.stream().map(mapper::toMovieResponse).toList();
    }

    public MovieResponse saveMovie(MovieRequest movieRequest) {
        List<Genre> genresList = genreRepository.findAllByIdIn(movieRequest.getGenreIds());
        Set<Genre> genres = new HashSet<>(genresList);
        if(genres.size() !=  movieRequest.getGenreIds().size()) {
            throw new EntityNotFoundException("Genre IDs not found");
        }
        Movie movie = mapper.toMovie(movieRequest, genres);
        Movie savedMovie = movieRepository.save(movie);
        return mapper.toMovieResponse(savedMovie);
    }

    @Transactional
    public MovieResponse updateMovie(Long Id,MovieRequest movieRequest) {
        Movie movie = getMovieById(Id);
        List<Genre> genresList = genreRepository.findAllByIdIn(movieRequest.getGenreIds());
        Set<Genre> genres = new HashSet<>(genresList);
        if(genres.size() !=  movieRequest.getGenreIds().size()) {
            throw new EntityNotFoundException("Genre IDs not found");
        }
        movie.setTitle(movieRequest.getTitle());
        movie.setDescription(movieRequest.getDescription());
        movie.setDuration(movieRequest.getDuration());
        movie.setImageUrl(movieRequest.getImageUrl());
        movie.setGenres(genres);
        Movie updatedMovie = movieRepository.save(movie);
        return mapper.toMovieResponse(updatedMovie);
    }

    @Transactional
    public void deleteMovie(Long id) {
        Movie movie = getMovieById(id);
        movieRepository.delete(movie);
    }

}
