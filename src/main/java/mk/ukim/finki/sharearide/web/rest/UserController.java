package mk.ukim.finki.sharearide.web.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharearide.model.User;
import mk.ukim.finki.sharearide.model.dto.UserDto;
import mk.ukim.finki.sharearide.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDto user) {
        User createdUser = this.userService.register(user);
        return ResponseEntity.ok(createdUser);
    }
}
