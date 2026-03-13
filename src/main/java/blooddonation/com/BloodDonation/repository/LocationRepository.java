package blooddonation.com.BloodDonation.repository;

import blooddonation.com.BloodDonation.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    boolean existsByCode(String code);
    Optional<Location> findByCode(String code);
    List<Location> findByType(Location.ELocationType type);
}

