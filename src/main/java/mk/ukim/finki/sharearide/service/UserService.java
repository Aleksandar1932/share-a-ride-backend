package mk.ukim.finki.sharearide.service;

import mk.ukim.finki.sharearide.model.User;
import mk.ukim.finki.sharearide.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(UserDto userDto);

    User findByUsername(String username);
}
