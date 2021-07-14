package mk.ukim.finki.sharearide.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharearide.model.City;
import mk.ukim.finki.sharearide.model.exceptions.CityDoesNotExistException;
import mk.ukim.finki.sharearide.repository.jpa.CityRepository;
import mk.ukim.finki.sharearide.service.CityService;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Override
    public City findByName(String name) {
        return this.cityRepository.findCityByName(name)
                .orElseThrow(() -> new CityDoesNotExistException(name));
    }
}
