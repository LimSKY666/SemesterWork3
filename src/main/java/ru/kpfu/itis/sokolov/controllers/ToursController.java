package ru.kpfu.itis.sokolov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.sokolov.dto.TourDto;
import ru.kpfu.itis.sokolov.models.City;
import ru.kpfu.itis.sokolov.models.Country;
import ru.kpfu.itis.sokolov.models.Tour;
import ru.kpfu.itis.sokolov.models.User;
import ru.kpfu.itis.sokolov.security.details.UserDetailsImpl;
import ru.kpfu.itis.sokolov.services.tourService.TourService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ToursController {

    @Autowired
    private TourService tourService;

    @GetMapping("/tours")
    public String getAllToursPage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        List<City> cities = new ArrayList<>();
        List<Tour> tours = tourService.getALlTours();
        List<Country> countries = new ArrayList<>();
        for (Tour tour: tours) {
            countries.addAll(tour.getCountries());
            for (Country country : countries) {
                cities.addAll(country.getCities());
            }
        }
        model.addAttribute("tours", tourService.getALlTours());
        model.addAttribute("countries", countries);
        model.addAttribute("cities", cities);
        User user = userDetails.getUser();
        model.addAttribute("user", userDetails.getUser());
        return "tours";
    }

    @PostMapping("/tours")
    public String createTour(TourDto tourDto) {
        tourService.saveTour(tourDto);
        return "redirect:/tours";
    }

    @GetMapping("/tours/add/{tour_id}/{user_id}")
    public String addTourToUser(@PathVariable("tour_id") Long tourId, @PathVariable("user_id") Long userId) {
        tourService.addTourToUser(userId, tourId);
        return "redirect:/tours";
    }
}
