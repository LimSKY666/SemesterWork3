package ru.kpfu.itis.sokolov.services.countriesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.sokolov.repositories.CountryRepository;
import ru.kpfu.itis.sokolov.models.Country;
import java.util.List;

@Service
public class CountriesServiceImpl implements CountriesService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
