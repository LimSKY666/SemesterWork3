package ru.kpfu.itis.sokolov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.sokolov.models.Tour;

public interface TourRepository extends JpaRepository<Tour, Long> {

}
