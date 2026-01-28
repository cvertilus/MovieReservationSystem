package com.MovieReservationSystem.movieService.controller;

import com.MovieReservationSystem.movieService.dto.AuditoriumRequest;
import com.MovieReservationSystem.movieService.dto.AuditoriumResponse;
import com.MovieReservationSystem.movieService.service.AuditoriumService;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/auditoriums")
@RequiredArgsConstructor
public class AuditoriumController {
    private final AuditoriumService auditoriumService;


    @GetMapping("/id")
    public ResponseEntity<AuditoriumResponse> getAuditoriumById(@PathVariable Long id) {
        AuditoriumResponse auditorium = auditoriumService.getAuditoriumById(id);
        return ResponseEntity.ok(auditorium);
    }

    @PostMapping()
    public ResponseEntity<AuditoriumResponse> createAuditorium(@RequestBody AuditoriumRequest request) {
        AuditoriumResponse createdAuditorium = auditoriumService.saveAuditorium(request);
        return new ResponseEntity<>(createdAuditorium, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<AuditoriumResponse>> getAllAuditorium() {
        List<AuditoriumResponse> auditoriums = auditoriumService.getAllAuditorium();
        return ResponseEntity.ok(auditoriums);
    }

}
