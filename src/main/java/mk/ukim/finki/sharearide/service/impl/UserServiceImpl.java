package mk.ukim.finki.sharearide.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharearide.model.User;
import mk.ukim.finki.sharearide.model.dto.UserDto;
import mk.ukim.finki.sharearide.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.sharearide.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.sharearide.model.exceptions.UserDoesNotExistException;
import mk.ukim.finki.sharearide.model.exceptions.UsernameExistsException;
import mk.ukim.finki.sharearide.repository.jpa.UserRepository;
import mk.ukim.finki.sharearide.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findById(username)
                .orElseThrow(() -> new UserDoesNotExistException(username));
    }

    @Override
    public User register(UserDto userDto) {
        if (userDto.getUsername() == null || userDto.getUsername().isEmpty() || userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            throw new InvalidUsernameOrPasswordException();
        }

        if (!userDto.getPassword().equals(userDto.getRepeatPassword())) {
            throw new PasswordsDoNotMatchException();
        }

        if (this.userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new UsernameExistsException(userDto.getUsername());
        }

        User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), userDto.getRole());
        return this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
