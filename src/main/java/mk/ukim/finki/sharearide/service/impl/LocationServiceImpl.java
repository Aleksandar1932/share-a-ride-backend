package mk.ukim.finki.sharearide.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharearide.model.Location;
import mk.ukim.finki.sharearide.model.exceptions.LocationDoesNotExistException;
import mk.ukim.finki.sharearide.repository.jpa.LocationRepository;
import mk.ukim.finki.sharearide.service.LocationService;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    @Override
    public Location findById(String id) {
        return this.locationRepository.findById(id)
                .orElseThrow(() -> new LocationDoesNotExistException(id));
    }
}
