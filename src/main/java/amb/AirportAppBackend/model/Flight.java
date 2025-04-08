package amb.AirportAppBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    public enum FlightStatus{
        ON_TIME, DELAYED, CANCELLED;
    }

    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "plane_id")
    private Plane plane;
}