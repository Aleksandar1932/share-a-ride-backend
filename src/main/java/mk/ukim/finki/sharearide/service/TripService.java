package mk.ukim.finki.sharearide.service;

import mk.ukim.finki.sharearide.model.Trip;
import mk.ukim.finki.sharearide.model.dto.TripDto;
import mk.ukim.finki.sharearide.model.dto.TripSearchCriteriaDto;
import mk.ukim.finki.sharearide.model.dto.UserTripDto;

import java.util.List;
import java.util.Optional;

public interface TripService {
    Optional<Trip> findById(String tripId);

    Optional<Trip> offer(TripDto tripDto);

    Optional<Trip> cancel(UserTripDto userTripDto);

    List<Trip> findPassenger(String username);

    List<Trip> findOffered(String username);

    List<Trip> findAll(String username);

    List<Trip> search(TripSearchCriteriaDto searchCriteriaDto);

    Optional<Trip> registerAsPassenger(UserTripDto userTripDto);
}
