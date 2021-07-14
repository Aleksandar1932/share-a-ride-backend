package mk.ukim.finki.sharearide.service;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharearide.model.City;
import mk.ukim.finki.sharearide.repository.jpa.CityRepository;

import java.util.Optional;

public interface CityService {

    City findByName(String name);
}
