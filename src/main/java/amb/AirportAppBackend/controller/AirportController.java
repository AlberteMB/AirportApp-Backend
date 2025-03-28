package amb.AirportAppBackend.controller;

import amb.AirportAppBackend.service.AirportService;
import amb.AirportAppBackend.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/airports")
@CrossOrigin(origins = "http://localhost:3000")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<Airport> getAllAirports() {
        return airportService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        return airportService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-coordinates")
    public ResponseEntity<List<Airport>> getAirportsByCoordinates(
            @RequestParam Double latitude,
            @RequestParam Double longitude) {
        List<Airport> airports = airportService.findAirportsByCoordinates(latitude, longitude);
        return ResponseEntity.ok(airports);
    }

    @PostMapping
    public Airport createAirport(@RequestBody Airport airport) {
        return airportService.save(airport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long id, @RequestBody Airport airport) {
        return airportService.findById(id)
                .map(existingAirport -> {
                    airport.setId(id);
                    return ResponseEntity.ok(airportService.save(airport));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
        return airportService.findById(id).map(airport ->
                    airportService.deleteById(id)
                    ? ResponseEntity.ok().<Void>build()
                    : ResponseEntity.internalServerError().<Void>build())
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Airport> searchAirports(@RequestParam String keyword) {
        return airportService.searchAirports(keyword);
    }
}
