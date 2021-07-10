package mk.ukim.finki.sharearide.model.exceptions;

public class DriverCannotBePassengerException extends RuntimeException {
    public DriverCannotBePassengerException(String username) {
        super(String.format("Driver with username: %s cannot be passenger.", username));
    }
}
