package ru.kpfu.itis.sokolov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.sokolov.models.Country;
import ru.kpfu.itis.sokolov.models.Tour;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourDto {

    private String name;
    private Double price;
    private List<String> countries;
    private Long cities;
    private Long hotels;
    private Long days;

    public static TourDto from(Tour tour) {
        return TourDto.builder()
                .price(tour.getPrice())
                .name(tour.getName())
                .cities(tour.getCity().getId())
                .hotels(tour.getHotel().getId())
                .days(tour.getDays())
                .countries(tour.getCountries().stream().map(Country::getName).collect(Collectors.toList()))
                .build();
    }

    public static List<TourDto> from(List<Tour> tours) {
        return tours.stream().map(TourDto::from).collect(Collectors.toList());
    }
}
