package amb.AirportAppBackend.repository;

import amb.AirportAppBackend.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaneRepository extends JpaRepository<Plane, Long> {


    @Query("SELECT p FROM Plane p WHERE p.model LIKE %:keyword% OR p.manufacturer LIKE %:keyword% OR p.registrationNumber LIKE %:keyword% OR p.yearOfManufacture LIKE %:keyword%")
    List<Plane> searchPlanes(@Param("keyword") String keyword);

    List<Plane> findByCapacity(Integer capacity);
}
