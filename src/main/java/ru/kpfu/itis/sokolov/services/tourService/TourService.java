package ru.kpfu.itis.sokolov.services.tourService;

import ru.kpfu.itis.sokolov.dto.TourDto;
import ru.kpfu.itis.sokolov.models.Tour;

import java.util.List;

public interface TourService {
    Tour saveTour(TourDto tourDto);
    List<Tour> getALlTours();
    List<Tour> getALlToursByUserId(long userId);
    void addTourToUser(Long userId, Long tourId);
    void deleteTourFromUser(Long userId, Long tourId);
}
