package blooddonation.com.BloodDonation.service;

import blooddonation.com.BloodDonation.controller.dto.CreateBloodRequestRequest;
import blooddonation.com.BloodDonation.domain.BloodBank;
import blooddonation.com.BloodDonation.domain.BloodRequest;
import blooddonation.com.BloodDonation.repository.BloodBankRepository;
import blooddonation.com.BloodDonation.repository.BloodRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BloodRequestService {

    private final BloodRequestRepository bloodRequestRepository;
    private final BloodBankRepository bloodBankRepository;

    public BloodRequestService(BloodRequestRepository bloodRequestRepository, BloodBankRepository bloodBankRepository) {
        this.bloodRequestRepository = bloodRequestRepository;
        this.bloodBankRepository = bloodBankRepository;
    }

    @Transactional
    public BloodRequest create(CreateBloodRequestRequest req) {
        BloodBank bank = bloodBankRepository.findById(req.bloodBankId())
                .orElseThrow(() -> new NotFoundException("Blood bank not found: " + req.bloodBankId()));

        BloodRequest r = new BloodRequest();
        r.setBloodBank(bank);
        r.setBloodType(req.bloodType());
        r.setUnits(req.units());
        r.setRequestDate(req.requestDate());
        return bloodRequestRepository.save(r);
    }

    @Transactional(readOnly = true)
    public List<BloodRequest> list() {
        return bloodRequestRepository.findAll();
    }
}

