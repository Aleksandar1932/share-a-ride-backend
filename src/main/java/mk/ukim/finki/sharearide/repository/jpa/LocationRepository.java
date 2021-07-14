package mk.ukim.finki.sharearide.repository.jpa;

import mk.ukim.finki.sharearide.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, String> {
}
