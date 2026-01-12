package com.MovieReservationSystem.movieService.controller;

import com.MovieReservationSystem.movieService.dto.MovieRequest;
import com.MovieReservationSystem.movieService.dto.MovieResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.MovieReservationSystem.movieService.service.MovieService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    @GetMapping("")
    public ResponseEntity<List<MovieResponse>> getMovies(){
        List<MovieResponse> movieResponses = movieService.getAllMovies();
        return ResponseEntity.ok(movieResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id){
        MovieResponse movieResponse = movieService.getMovieResponseById(id);
        return ResponseEntity.ok(movieResponse);
    }

    @PostMapping()
    public ResponseEntity<MovieResponse> createMovie(@Valid @RequestBody MovieRequest movieRequest){
        MovieResponse movieResponse = movieService.saveMovie(movieRequest);
        return new ResponseEntity<>(movieResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable Long id,@Valid @RequestBody MovieRequest movieRequest) {
        MovieResponse movieResponse = movieService.updateMovie(id, movieRequest);
        return ResponseEntity.ok(movieResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }


}
