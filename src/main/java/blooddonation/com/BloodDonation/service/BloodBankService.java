package blooddonation.com.BloodDonation.service;

import blooddonation.com.BloodDonation.controller.dto.CreateBloodBankRequest;
import blooddonation.com.BloodDonation.domain.BloodBank;
import blooddonation.com.BloodDonation.domain.Location;
import blooddonation.com.BloodDonation.repository.BloodBankRepository;
import blooddonation.com.BloodDonation.repository.LocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BloodBankService {

    private final BloodBankRepository bloodBankRepository;
    private final LocationRepository locationRepository;

    public BloodBankService(BloodBankRepository bloodBankRepository, LocationRepository locationRepository) {
        this.bloodBankRepository = bloodBankRepository;
        this.locationRepository = locationRepository;
    }

    @Transactional
    public BloodBank create(CreateBloodBankRequest req) {
        if (bloodBankRepository.existsByName(req.name())) {
            throw new BadRequestException("Blood bank name already exists: " + req.name());
        }
        Location village = locationRepository.findByCode(req.villageCode())
                .orElseThrow(() -> new NotFoundException("Village not found: " + req.villageCode()));
        if (village.getType() != Location.ELocationType.VILLAGE) {
            throw new BadRequestException("Location code is not a VILLAGE: " + req.villageCode());
        }

        BloodBank b = new BloodBank();
        b.setName(req.name());
        b.setAddressLine(req.addressLine());
        b.setVillageLocation(village);
        return bloodBankRepository.save(b);
    }

    @Transactional(readOnly = true)
    public List<BloodBank> list() {
        return bloodBankRepository.findAll();
    }

    @Transactional(readOnly = true)
    public BloodBank get(Long id) {
        return bloodBankRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Blood bank not found: " + id));
    }
}

