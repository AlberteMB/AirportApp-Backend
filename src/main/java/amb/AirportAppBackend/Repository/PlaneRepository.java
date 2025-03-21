package amb.AirportAppBackend.Repository;

import amb.AirportAppBackend.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane, Long> {

}
