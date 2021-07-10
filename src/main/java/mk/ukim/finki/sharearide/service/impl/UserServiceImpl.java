package mk.ukim.finki.sharearide.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharearide.model.User;
import mk.ukim.finki.sharearide.model.exceptions.UserDoesNotExistException;
import mk.ukim.finki.sharearide.repository.jpa.UserRepository;
import mk.ukim.finki.sharearide.service.UserService;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findById(username)
                .orElseThrow(() -> new UserDoesNotExistException(username));
    }
}
