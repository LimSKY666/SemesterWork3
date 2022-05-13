package ru.kpfu.itis.sokolov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.sokolov.models.City;
import ru.kpfu.itis.sokolov.models.Country;
import ru.kpfu.itis.sokolov.models.Tour;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto {

    private Long id;
    private String name;
    private List<City> cities;
    private List<Tour> tours;

    public static CountryDto from(Country country) {
        return CountryDto.builder()
                .id(country.getId())
                .cities(country.getCities())
                .tours(country.getTours())
                .build();
    }

    public static List<CountryDto> from(List<Country> countries) {
        return countries.stream().map(CountryDto::from).collect(Collectors.toList());
    }
}
