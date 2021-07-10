package mk.ukim.finki.sharearide.service;

import mk.ukim.finki.sharearide.model.User;

public interface UserService {
    User findByUsername(String username);
}
