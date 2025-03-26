package amb.AirportAppBackend.repository;

import amb.AirportAppBackend.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long>, JpaSpecificationExecutor<Airport> {

    // Find airports by country
    List<Airport> findByRegion(String region);

    // Find airports by city
    List<Airport> findByCity(String city);

    // Find airport by code
    Airport findByCode(String code);

    // Find by latitude and longitude. 0.0001 = 11 meters of difference.
    @Query("SELECT a FROM Airport a WHERE ABS(a.latitude - :latitude) < 0.0001 AND ABS(a.longitude - :longitude) < 0.0001")
    List<Airport> findNearbyAirports(@Param("latitude") Double latitude, @Param("longitude") Double longitude);


    // Custom query example
    @Query("SELECT a FROM Airport a WHERE a.name LIKE %:keyword% OR a.code LIKE %:keyword% OR a.city LIKE %:keyword% OR a.region LIKE %:keyword%")
    List<Airport> searchAirports(@Param("keyword") String keyword);
}