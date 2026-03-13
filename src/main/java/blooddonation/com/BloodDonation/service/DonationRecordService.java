package blooddonation.com.BloodDonation.service;

import blooddonation.com.BloodDonation.controller.dto.CreateDonationRecordRequest;
import blooddonation.com.BloodDonation.domain.BloodBank;
import blooddonation.com.BloodDonation.domain.DonationRecord;
import blooddonation.com.BloodDonation.domain.Donor;
import blooddonation.com.BloodDonation.repository.BloodBankRepository;
import blooddonation.com.BloodDonation.repository.DonationRecordRepository;
import blooddonation.com.BloodDonation.repository.DonorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DonationRecordService {

    private final DonationRecordRepository donationRecordRepository;
    private final DonorRepository donorRepository;
    private final BloodBankRepository bloodBankRepository;

    public DonationRecordService(
            DonationRecordRepository donationRecordRepository,
            DonorRepository donorRepository,
            BloodBankRepository bloodBankRepository
    ) {
        this.donationRecordRepository = donationRecordRepository;
        this.donorRepository = donorRepository;
        this.bloodBankRepository = bloodBankRepository;
    }

    @Transactional
    public DonationRecord create(CreateDonationRecordRequest req) {
        Donor donor = donorRepository.findById(req.donorId())
                .orElseThrow(() -> new NotFoundException("Donor not found: " + req.donorId()));
        BloodBank bank = bloodBankRepository.findById(req.bloodBankId())
                .orElseThrow(() -> new NotFoundException("Blood bank not found: " + req.bloodBankId()));

        DonationRecord r = new DonationRecord();
        r.setDonor(donor);
        r.setBloodBank(bank);
        r.setDonationDate(req.donationDate());
        r.setUnits(req.units());
        return donationRecordRepository.save(r);
    }

    @Transactional(readOnly = true)
    public Page<DonationRecord> list(Pageable pageable) {
        return donationRecordRepository.findAll(pageable);
    }
}

