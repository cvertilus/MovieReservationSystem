package com.MovieReservationSystem.movieService.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "auditoriums")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Auditorium {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private int capacity;
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL)
    private Set<Showtime> showtimes = new HashSet<>();


    public void addShowtime(Showtime showtime) {
        showtimes.add(showtime);
        showtime.setAuditorium(this);
    }
}
