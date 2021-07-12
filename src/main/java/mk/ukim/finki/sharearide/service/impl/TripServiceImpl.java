package mk.ukim.finki.sharearide.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharearide.model.City;
import mk.ukim.finki.sharearide.model.Location;
import mk.ukim.finki.sharearide.model.Trip;
import mk.ukim.finki.sharearide.model.dto.TripDto;
import mk.ukim.finki.sharearide.model.dto.TripSearchCriteriaDto;
import mk.ukim.finki.sharearide.model.dto.UserTripDto;
import mk.ukim.finki.sharearide.model.enumerations.TripStatus;
import mk.ukim.finki.sharearide.model.exceptions.*;
import mk.ukim.finki.sharearide.repository.jpa.TripRepository;
import mk.ukim.finki.sharearide.service.CityService;
import mk.ukim.finki.sharearide.service.LocationService;
import mk.ukim.finki.sharearide.service.TripService;
import mk.ukim.finki.sharearide.model.User;
import mk.ukim.finki.sharearide.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;

    private final CityService cityService;
    private final UserService userService;
    private final LocationService locationService;


    @Override
    public Optional<Trip> findById(String tripId) {
        return Optional.of(this.tripRepository.findById(tripId).orElseThrow(() -> new TripDoesNotExistException(tripId)));
    }

    @Override
    public Optional<Trip> offer(TripDto tripDto) {
        User driver = this.userService.findByUsername(tripDto.getDriverUsername());

        City origin = this.cityService.findByName(tripDto.getOriginName());

        City destination = this.cityService.findByName(tripDto.getDestinationName());

        Location meetingPlace = this.locationService.findById(tripDto.getMeetingPlaceId());

        Trip trip = new Trip(tripDto.getDescription(),
                tripDto.getPrice(),
                origin,
                destination,
                LocalDateTime.parse(tripDto.getDeparture(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                meetingPlace,
                tripDto.getOfferedSeats(),
                driver
        );

        return Optional.of(this.tripRepository.save(trip));
    }

    @Override
    public Optional<Trip> cancel(UserTripDto userTripDto) {
        User user = this.userService.findByUsername(userTripDto.getUsername());

        Trip trip = this.findById(userTripDto.getTripId())
                .orElseThrow(() -> new TripDoesNotExistException(userTripDto.getTripId()));
        boolean isDriver = trip.getDriver().equals(user);

        if (isDriver) {
            // TODO: Publish Driver Canceled Trip Event
            trip.setStatus(TripStatus.CANCELED);
        }

        boolean isPassenger = trip.getPassengers().contains(user);
        if (!isDriver && !isPassenger) {
            throw new UserNeitherDriverOrPassengerOnTripException(userTripDto.getUsername(), userTripDto.getTripId());
        }

        if (isPassenger) {
            trip.getPassengers().remove(user);
        }

        return Optional.of(this.tripRepository.save(trip));
    }

    @Override
    public List<Trip> findPassenger(String username) {
        return this.tripRepository.findTripsByPassengersUsernameContaining(username);
    }

    @Override
    public List<Trip> findOffered(String username) {
        return this.tripRepository.findTripsByDriverUsername(username);
    }

    @Override
    public List<Trip> findAll(String username) {
        List<Trip> asPassenger = this.findPassenger(username);
        List<Trip> asDriver = this.findOffered(username);

        return Stream.of(asPassenger, asDriver)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<Trip> search(TripSearchCriteriaDto searchCriteriaDto) {
        City origin = this.cityService.findByName(searchCriteriaDto.getOriginName());
        City destination = this.cityService.findByName(searchCriteriaDto.getDestinationName());

        return this.tripRepository.findTripsByDestinationAndOriginAndDepartureBetweenAndPrice(
                origin,
                destination,
                searchCriteriaDto.getDepartureTimeFrom(),
                searchCriteriaDto.getDepartureTimeTo(),
                searchCriteriaDto.getPrice());
    }

    @Override
    public Optional<Trip> registerAsPassenger(UserTripDto userTripDto) {
        User user = this.userService.findByUsername(userTripDto.getUsername());
        Trip trip = this.findById(userTripDto.getTripId()).orElseThrow(() -> new TripDoesNotExistException(userTripDto.getTripId()));

        if (trip.getDriver().equals(user)) {
            throw new DriverCannotBePassengerException(userTripDto.getUsername());
        }

        trip.getPassengers().add(user);

        return Optional.of(this.tripRepository.save(trip));
    }
}
