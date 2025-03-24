package amb.AirportAppBackend.Controller;


import amb.AirportAppBackend.Service.FlightService;
import amb.AirportAppBackend.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public Page<Flight> getAllFlights(Pageable pageable){
        return flightService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id){
        return flightService.findById(id).
                map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight){
        return flightService.save(flight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flight, Flight.FlightStatus status){
        return flightService.findById(id).map(existingFlight -> {
            flight.setId(id);
            flight.setStatus(status);
            return ResponseEntity.ok(flightService.save(flight));
            }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id){
        return flightService.findById(id).map(flight ->
            flightService.deleteById(id)
            ? ResponseEntity.ok().<Void>build()
            : ResponseEntity.internalServerError().<Void>build())
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Flight> searchFlight(@RequestParam String keyword) {
        return flightService.searchFlights(keyword);
    }


}
