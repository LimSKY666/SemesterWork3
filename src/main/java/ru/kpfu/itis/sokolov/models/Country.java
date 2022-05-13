package ru.kpfu.itis.sokolov.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "country")
    private List<City> cities;

    @ToString.Exclude
    @ManyToMany(mappedBy = "countries")
    private List<Tour> tours;
}
