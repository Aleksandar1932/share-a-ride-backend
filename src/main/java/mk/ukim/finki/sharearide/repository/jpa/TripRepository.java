package mk.ukim.finki.sharearide.repository.jpa;

import mk.ukim.finki.sharearide.model.City;
import mk.ukim.finki.sharearide.model.Trip;
import mk.ukim.finki.sharearide.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, String> {
    List<Trip> findTripsByDestinationAndOriginAndDepartureBetweenAndPrice(City destination, City origin, LocalDateTime from, LocalDateTime to, Double price);

    List<Trip> findTripsByDriverUsername(String username);

    List<Trip> findTripsByPassengersUsernameContaining(String username);
}
