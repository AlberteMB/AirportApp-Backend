package amb.AirportAppBackend.service;

import amb.AirportAppBackend.repository.FlightRepository;
import amb.AirportAppBackend.model.Flight;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public Page<Flight> findAll(Pageable pageable){
        return flightRepository.findAll(pageable);
    }

    public Optional<Flight> findById(Long id) {
        return flightRepository.findById(id);
    }

    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    @Transactional
    public boolean deleteById(Long id) {
        return flightRepository.findById(id)
                .map(flight -> {
                    flightRepository.delete(flight);
                    return true;
                })
                .orElse(false); // Si no existe, devolvemos false
    }

    public Long countFlights() {
        return flightRepository.count();
    }


    public List<Flight> searchFlights(String keyword) {
        return flightRepository.searchFlights(keyword);
    }


}
