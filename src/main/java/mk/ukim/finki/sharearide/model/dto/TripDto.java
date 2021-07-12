package mk.ukim.finki.sharearide.model.dto;

import lombok.Data;

@Data
public class TripDto {
    String description;
    Double price;
    String originName;
    String destinationName;
    String departure;
    String meetingPlaceId;
    Integer offeredSeats;
    String driverUsername;
}
