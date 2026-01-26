package com.MovieReservationSystem.movieService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="showtimes")
public class Showtime {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="movie_id", nullable=false)
    private Movie movie;

    @Builder.Default
    @ManyToOne
    @JoinColumn(name = "auditorium_id")
    @JsonBackReference // El "hijo" oculta la referencia en el JSON para evitar bucles
    private Auditorium auditorium;

}
