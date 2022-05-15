package ru.kpfu.itis.sokolov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.sokolov.models.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
