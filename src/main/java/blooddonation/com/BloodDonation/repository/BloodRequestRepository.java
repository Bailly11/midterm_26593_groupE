package blooddonation.com.BloodDonation.repository;

import blooddonation.com.BloodDonation.domain.BloodRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodRequestRepository extends JpaRepository<BloodRequest, Long> {
}

