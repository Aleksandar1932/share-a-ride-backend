package mk.ukim.finki.sharearide.model.exceptions;

public class CityDoesNotExistException extends RuntimeException {
    public CityDoesNotExistException(String message) {
        super(String.format("City with name: %s does not exist", message));
    }
}
