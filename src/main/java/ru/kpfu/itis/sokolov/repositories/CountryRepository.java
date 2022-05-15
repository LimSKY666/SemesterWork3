package ru.kpfu.itis.sokolov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.sokolov.models.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
