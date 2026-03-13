package blooddonation.com.BloodDonation.repository;

import blooddonation.com.BloodDonation.domain.DonationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRecordRepository extends JpaRepository<DonationRecord, Long> {
}

