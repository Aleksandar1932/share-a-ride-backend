package mk.ukim.finki.sharearide.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TripSearchCriteriaDto {
    String destinationName;
    String originName;
    LocalDateTime departureTimeFrom;
    LocalDateTime departureTimeTo;
    Double price;
}   
