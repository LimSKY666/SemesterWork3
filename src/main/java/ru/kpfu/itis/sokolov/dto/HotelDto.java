package ru.kpfu.itis.sokolov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.sokolov.models.Hotel;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {

    private Long id;
    private String name;
    private Long stars;

    public static HotelDto from(Hotel hotel) {
        return HotelDto.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .stars(hotel.getStars())
                .build();
    }

    public static List<HotelDto> from(List<Hotel> hotels) {
        return hotels.stream().map(HotelDto::from).collect(Collectors.toList());
    }
}
