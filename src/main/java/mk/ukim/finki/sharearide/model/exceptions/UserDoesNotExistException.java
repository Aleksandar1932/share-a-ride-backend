package mk.ukim.finki.sharearide.model.exceptions;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(String username) {
        super(String.format("User with username: %s does not exist", username));
    }
}

