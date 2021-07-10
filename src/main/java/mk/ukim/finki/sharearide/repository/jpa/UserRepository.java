package mk.ukim.finki.sharearide.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import mk.ukim.finki.sharearide.model.User;

public interface UserRepository extends JpaRepository<User, String> {
}
