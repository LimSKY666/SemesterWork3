package ru.kpfu.itis.sokolov.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private Double price;

    private Long days;

    @ManyToMany
    @JoinTable(name = "tour_country",
            joinColumns = {@JoinColumn(name = "tour_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "country_id", referencedColumnName = "id")})
    private List<Country> countries;

    @ToString.Exclude
    @ManyToMany(mappedBy = "tours")
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
}
