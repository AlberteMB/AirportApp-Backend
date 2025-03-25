package amb.AirportAppBackend.service;

import amb.AirportAppBackend.repository.PlaneRepository;
import amb.AirportAppBackend.model.Plane;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class PlaneService {

    private final PlaneRepository planeRepository;

    public PlaneService(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    public List<Plane> findAll() {
        return planeRepository.findAll();
    }

    public Page<Plane> findAll(Pageable pageable){
        return planeRepository.findAll(pageable);

    }

    public Plane save(Plane plane) {
        return planeRepository.save(plane);
    }

    public Optional<Plane> findById(Long id) {
        return planeRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(Long id) {
        return planeRepository.findById(id)
                .map(plane -> {
                    // Check if the airport has associated flights
                    if (!plane.getFlights().isEmpty()) {
                        throw new IllegalStateException("It cannot be deleted because it has associated flights");
                    }

                    planeRepository.delete(plane);
                    return true;
                })
                .orElse(false);
    }

    public List<Plane> findByCapacity(Integer capacity) {
        return planeRepository.findByCapacity(capacity);
    }


    public List<Plane> searchPlanes(String keyword) {
        return planeRepository.searchPlanes(keyword);
    }




}
