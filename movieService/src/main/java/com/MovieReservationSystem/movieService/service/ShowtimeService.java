package com.MovieReservationSystem.movieService.service;

import com.MovieReservationSystem.movieService.dto.DtoMapper;
import com.MovieReservationSystem.movieService.dto.ShowTimeRequest;
import com.MovieReservationSystem.movieService.dto.ShowtimeResponse;
import com.MovieReservationSystem.movieService.model.Auditorium;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.MovieReservationSystem.movieService.model.Movie;
import com.MovieReservationSystem.movieService.model.Showtime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.MovieReservationSystem.movieService.repository.ShowtimeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowtimeService {
    private final ShowtimeRepository showtimeRepository;
    private final DtoMapper mapper;
    private final MovieService movieService;
    private final AuditoriumService auditoriumService;



    public List<ShowtimeResponse> getAllShowtimes() {
        List <Showtime> showtimes = showtimeRepository.findAll();
        return showtimes.stream().map(mapper::toShowtimeResponse).toList();
    }

    public ShowtimeResponse getShowtimeById(Long id) {
        Showtime showtime = showtimeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No showtime found with id " + id));
        return mapper.toShowtimeResponse(showtime);
    }

    @Transactional
    public ShowtimeResponse createShowtime(ShowTimeRequest showTimeRequest, Long movieId) {
        Movie movie = movieService.getMovieById(movieId);
        Auditorium auditorium = auditoriumService.getAuditoriumEntityById(showTimeRequest.getAuditoriumId());
        Showtime showtime = mapper.toShowtime(showTimeRequest, movie, auditorium);
        Showtime savedShowtime = showtimeRepository.save(showtime);
        return mapper.toShowtimeResponse(savedShowtime);
    }

    @Transactional
    public void deleteShowtime(Long id) {
        Showtime showtime = showtimeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No showtime found with id " + id));
        Movie movie = showtime.getMovie();

        if (movie != null) {
            movie.removeShowtime(showtime);
        }
        Auditorium auditorium = showtime.getAuditorium();
        if (auditorium != null) {
            auditorium.removeShowtime(showtime);
        }
        showtimeRepository.delete(showtime);
    }


}
