package blooddonation.com.BloodDonation.repository;

import blooddonation.com.BloodDonation.domain.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {
    boolean existsByName(String name);
}

