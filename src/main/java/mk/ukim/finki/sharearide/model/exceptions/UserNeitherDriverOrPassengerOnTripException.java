package mk.ukim.finki.sharearide.model.exceptions;

public class UserNeitherDriverOrPassengerOnTripException extends RuntimeException {
    public UserNeitherDriverOrPassengerOnTripException(String username, String tripId) {
        super(String.format("User with username: %s is neither passenger or driver on trip with id: %s", username, tripId));
    }
}
