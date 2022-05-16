package ru.kpfu.itis.sokolov.services.hotelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.sokolov.repositories.HotelRepository;
import ru.kpfu.itis.sokolov.models.Hotel;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
