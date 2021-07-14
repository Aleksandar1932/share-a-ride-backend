package mk.ukim.finki.sharearide.model.exceptions;

public class LocationDoesNotExistException extends RuntimeException {
    public LocationDoesNotExistException(String message) {
        super(String.format("City with name: %s does not exist", message));
    }
}
