package blooddonation.com.BloodDonation.repository;

import blooddonation.com.BloodDonation.domain.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    Optional<Campaign> findByName(String name);
    boolean existsByName(String name);
}

