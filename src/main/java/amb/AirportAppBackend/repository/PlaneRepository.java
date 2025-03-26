package amb.AirportAppBackend.repository;

import amb.AirportAppBackend.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Long> {


    @Query("SELECT p FROM Plane p WHERE p.model LIKE %:keyword% OR p.manufacturer LIKE %:keyword% OR p.registrationNumber LIKE %:keyword% OR CAST(p.yearOfManufacture AS string) LIKE %:keyword%")
    List<Plane> searchPlanes(@Param("keyword") String keyword);

    List<Plane> findByCapacity(Integer capacity);
}
