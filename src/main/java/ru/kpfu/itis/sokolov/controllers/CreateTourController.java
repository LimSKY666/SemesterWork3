package ru.kpfu.itis.sokolov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.sokolov.dto.TourDto;
import ru.kpfu.itis.sokolov.models.Country;
import ru.kpfu.itis.sokolov.services.countriesService.CountriesService;
import ru.kpfu.itis.sokolov.services.hotelService.HotelService;
import ru.kpfu.itis.sokolov.services.tourService.TourService;

import java.util.List;

@Controller
public class CreateTourController {

    @Autowired
    private TourService tourService;

    @Autowired
    private CountriesService countriesService;

    @Autowired
    private HotelService hotelService;

    @GetMapping("/create-tour")
    public String getCreateTourPage(Model model) {
        List<Country> countries = countriesService.getAllCountries();
        model.addAttribute("countries", countries);
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "create-tour";
    }

    @PostMapping("/create-tour")
    public void createTour(TourDto tourDto) {

    }
}

