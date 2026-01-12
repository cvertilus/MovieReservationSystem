package com.MovieReservationSystem.movieService.controller;

import com.MovieReservationSystem.movieService.dto.ShowTimeRequest;
import com.MovieReservationSystem.movieService.dto.ShowtimeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.MovieReservationSystem.movieService.service.ShowtimeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ShowtimeController {
    public final ShowtimeService showtimeService;

    @GetMapping("/showtimes/{id}")
    public ResponseEntity<ShowtimeResponse> getShowtimeById(@PathVariable Long id) {
        ShowtimeResponse showtimeResponse = showtimeService.getShowtimeById(id);
        return ResponseEntity.ok(showtimeResponse);
    }

    @GetMapping("/showtimes")
    public ResponseEntity<List<ShowtimeResponse>> getAllShowtimes() {
        List<ShowtimeResponse> showtimeResponses = showtimeService.getAllShowtimes();
        return ResponseEntity.ok(showtimeResponses);
    }

    @PostMapping("/movies/{movieId}/showtime")
    public ResponseEntity<ShowtimeResponse> createShowtime(@Valid @RequestBody ShowTimeRequest showTimeRequest,@PathVariable Long movieId){
        ShowtimeResponse showtimeResponse = showtimeService.createShowtime(showTimeRequest,movieId);
        return new ResponseEntity<>(showtimeResponse,HttpStatus.CREATED);

    }

    @DeleteMapping("/showtimes/{showtimeId}")
    public ResponseEntity<Void> deleteShowtime(@PathVariable Long showtimeId){
        showtimeService.deleteShowtime(showtimeId);
        return ResponseEntity.noContent().build();
    }

}
