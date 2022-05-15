package ru.kpfu.itis.sokolov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.sokolov.models.City;

public interface CityRepository extends JpaRepository<City, Long> {
}
