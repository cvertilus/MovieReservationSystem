package com.MovieReservationSystem.movieService.model;
import com.MovieReservationSystem.movieService.model.Showtime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name = "auditoriums")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Auditorium {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private int capacity;
    private String name;

    @Builder.Default
    @JsonManagedReference
    @OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Showtime> showtimes = new HashSet<>();


    public void addShowtime(Showtime showtime) {
        showtimes.add(showtime);
        showtime.setAuditorium(this);
    }

    public void removeShowtime(Showtime showtime) {
        showtimes.remove(showtime);
        showtime.setAuditorium(null);
    }
}
