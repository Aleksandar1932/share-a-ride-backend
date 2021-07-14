package mk.ukim.finki.sharearide.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import mk.ukim.finki.sharearide.model.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
