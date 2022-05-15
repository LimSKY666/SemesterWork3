package ru.kpfu.itis.sokolov.services.tourService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.sokolov.dto.TourDto;
import ru.kpfu.itis.sokolov.models.City;
import ru.kpfu.itis.sokolov.models.Hotel;
import ru.kpfu.itis.sokolov.models.Tour;
import ru.kpfu.itis.sokolov.models.User;
import ru.kpfu.itis.sokolov.repositories.CityRepository;
import ru.kpfu.itis.sokolov.repositories.HotelRepository;
import ru.kpfu.itis.sokolov.repositories.TourRepository;
import ru.kpfu.itis.sokolov.repositories.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Tour saveTour(TourDto tourDto) {
        Tour tour = Tour.builder()
                .countries(Collections.emptyList())
                .days(tourDto.getDays())
                .price(tourDto.getPrice())
                .name(tourDto.getName())
                .users(Collections.emptyList())
                .build();

        City city = cityRepository.findById(tourDto.getCities()).orElse(null);

        Hotel hotel = hotelRepository.findById(tourDto.getHotels()).orElse(null);

        tour.setCity(city);
        tour.setHotel(hotel);

        return tourRepository.save(tour);
    }

    @Override
    public List<Tour> getALlTours() {
        return tourRepository.findAll();
    }

    @Override
    public List<Tour> getALlToursByUserId(long userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getTours();
    }

    @Override
    public void addTourToUser(Long userId, Long tourId) {
        Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new IllegalArgumentException("Tour not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (!user.getTours().contains(tour)) {
            user.getTours().add(tour);
            tour.getUsers().add(user);

            tourRepository.save(tour);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Tour is exist");
        }
    }

    @Override
    public void deleteTourFromUser(Long userId, Long tourId) {
        Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new IllegalArgumentException("Tour not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (user.getTours().contains(tour)) {
            user.getTours().remove(tour);
        } else {
            throw new IllegalArgumentException("Tour isn't exist");
        }
    }
}
