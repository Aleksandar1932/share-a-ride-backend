package mk.ukim.finki.sharearide.web.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharearide.model.Trip;
import mk.ukim.finki.sharearide.model.dto.TripDto;
import mk.ukim.finki.sharearide.model.dto.TripSearchCriteriaDto;
import mk.ukim.finki.sharearide.model.dto.UserTripDto;
import mk.ukim.finki.sharearide.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;


@RestController
@RequestMapping("/api/trip")
@AllArgsConstructor
public class TripController {
    private final TripService tripService;

    @PostMapping("/offer")
    public ResponseEntity<Trip> offer(@RequestBody TripDto tripDto) {
        return this.tripService.offer(tripDto)
                .map(trip -> ResponseEntity.ok().body(trip))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/cancel")
    public ResponseEntity<Trip> cancel(@RequestBody UserTripDto userTripDto) {
        return this.tripService.cancel(userTripDto)
                .map(trip -> ResponseEntity.ok().body(trip))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/my/passenger")
    public ResponseEntity<Collection<Trip>> passenger(Principal principal) {
        String username = principal.getName();
        Collection<Trip> trips = this.tripService.findPassenger(username);

        return ResponseEntity.ok().body(trips);
    }

    @GetMapping("/my/offered")
    public ResponseEntity<Collection<Trip>> offered(Principal principal) {
        String username = principal.getName();
        Collection<Trip> trips = this.tripService.findOffered(username);
        return ResponseEntity.ok().body(trips);
    }

    @GetMapping("/my/all")
    public ResponseEntity<Collection<Trip>> all(Principal principal) {
        String username = principal.getName();
        Collection<Trip> trips = this.tripService.findAll(username);
        return ResponseEntity.ok().body(trips);
    }

    @GetMapping("/search")
    public ResponseEntity<Collection<Trip>> search(@RequestBody TripSearchCriteriaDto tripSearchCriteriaDto) {
        Collection<Trip> trips = this.tripService.search(tripSearchCriteriaDto);
        return ResponseEntity.ok(trips);
    }

    @PostMapping("/register")
    public ResponseEntity<Trip> register(@RequestBody UserTripDto userTripDto) {
        return this.tripService.registerAsPassenger(userTripDto)
                .map(trip -> ResponseEntity.ok().body(trip))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
