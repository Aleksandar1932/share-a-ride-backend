package mk.ukim.finki.sharearide.repository.jpa;

import mk.ukim.finki.sharearide.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, String> {
    Optional<City> findCityByName(String name);
}
