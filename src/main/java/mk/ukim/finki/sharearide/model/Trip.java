package mk.ukim.finki.sharearide.model;

import lombok.Data;
import mk.ukim.finki.sharearide.model.enumerations.TripStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
public class Trip {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    private TripStatus status;
    private String description;
    private Double price;

    @ManyToOne
    private City origin;
    @ManyToOne
    private City destination;
    private LocalDateTime departure;
    @ManyToOne
    private Location meetingPlace;

    private Integer offeredSeats;

    @ManyToOne
    private User driver;

    @ManyToMany
    private Set<User> passengers;

    @OneToOne
    private MessageThread messageThread;

}
