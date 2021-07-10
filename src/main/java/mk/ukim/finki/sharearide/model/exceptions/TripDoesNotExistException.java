package mk.ukim.finki.sharearide.model.exceptions;

public class TripDoesNotExistException extends RuntimeException {
    public TripDoesNotExistException(String tripId) {
        super(String.format("Trip with id: %s does not exist", tripId));
    }
}
