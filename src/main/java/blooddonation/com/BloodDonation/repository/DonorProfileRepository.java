package blooddonation.com.BloodDonation.repository;

import blooddonation.com.BloodDonation.domain.DonorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorProfileRepository extends JpaRepository<DonorProfile, Long> {
}

