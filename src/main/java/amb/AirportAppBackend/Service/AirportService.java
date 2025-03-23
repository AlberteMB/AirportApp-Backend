package amb.AirportAppBackend.Service;

import amb.AirportAppBackend.Repository.AirportRepository;
import amb.AirportAppBackend.model.Airport;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> findAll() {
        return airportRepository.findAll();
    }

    public Page<Airport> findAll(Pageable pageable) {
        return airportRepository.findAll(pageable);
    }

    public Page<Airport> findAll(Specification<Airport> spec, Pageable pageable) {
        return airportRepository.findAll(spec, pageable);
    }

    public Optional<Airport> findById(Long id) {
        return airportRepository.findById(id);
    }

    public Airport save(Airport airport) {
        return airportRepository.save(airport);
    }

    @Transactional
    public boolean deleteById(Long id) {
        return airportRepository.findById(id)
                .map(airport -> {
                    // Check if the airport has associated flights
                    if (!airport.getDepartingFlights().isEmpty() || !airport.getArrivingFlights().isEmpty()) {
                        throw new IllegalStateException("It cannot be deleted because it has associated flights");
                    }

                    airportRepository.delete(airport);
                    return true;
                })
                .orElse(false);
    }


    public List<Airport> searchAirports(String keyword) {
        return airportRepository.searchAirports(keyword);
    }
}