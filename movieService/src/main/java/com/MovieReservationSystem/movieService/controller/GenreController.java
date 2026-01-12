package com.MovieReservationSystem.movieService.controller;

import com.MovieReservationSystem.movieService.dto.GenreRequest;
import com.MovieReservationSystem.movieService.dto.GenreResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.MovieReservationSystem.movieService.service.GenreService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {
    private final GenreService genreService;

    @GetMapping("")
    public ResponseEntity<List<GenreResponse>> getAllGenres() {
        List<GenreResponse> genres = genreService.getAllGenres();
        return ResponseEntity.ok(genres);
    }

    @PostMapping("")
    public ResponseEntity<GenreResponse> createGenre(@Valid @RequestBody GenreRequest genreRequest) {
        GenreResponse genreResponse = genreService.createGenre(genreRequest);
        // Implementation for creating a genre would go here
        return new ResponseEntity<>(genreResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponse> getGenreById(@PathVariable Long id) {
        GenreResponse genreResponse = genreService.getGenreResponseById(id);
        return  ResponseEntity.ok(genreResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }

}
