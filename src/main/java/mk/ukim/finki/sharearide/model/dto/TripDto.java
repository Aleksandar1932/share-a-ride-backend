package mk.ukim.finki.sharearide.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TripDto {
    String description;
    Double price;
    String originName;
    String destinationName;
    LocalDateTime departure;
    String meetingPlaceId;
    Integer offeredSeats;
    String driverUsername;
}
