package com.br.cassiogamarra.projeto3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@Table(name="film")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Film {

    @Id
    @Column(name="film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name="release_year")
    private int releaseYear;

    @Column(name="rental_duration")
    private int rentalDuration;

    @Column(name="rental_rate")
    private double rentalRate;

    @Column(name="length")
    private int length;

    @Column(name="replacement_cost")
    private double replacementCost;

    @Column(name="rating")
    private String rating;

    @Column(name="special_features")
    private String specialFeatures;

    @Column(name="last_update", columnDefinition = "timestamp")
    private LocalDateTime lastUpdate;

}
