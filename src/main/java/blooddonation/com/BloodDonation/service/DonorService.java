package blooddonation.com.BloodDonation.service;

import blooddonation.com.BloodDonation.controller.dto.CreateDonorProfileRequest;
import blooddonation.com.BloodDonation.controller.dto.CreateDonorRequest;
import blooddonation.com.BloodDonation.controller.dto.DonorResponse;
import blooddonation.com.BloodDonation.domain.Donor;
import blooddonation.com.BloodDonation.domain.DonorProfile;
import blooddonation.com.BloodDonation.domain.Location;
import blooddonation.com.BloodDonation.repository.DonorProfileRepository;
import blooddonation.com.BloodDonation.repository.DonorRepository;
import blooddonation.com.BloodDonation.repository.LocationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DonorService {

    private final DonorRepository donorRepository;
    private final LocationRepository locationRepository;
    private final DonorProfileRepository donorProfileRepository;

    public DonorService(
            DonorRepository donorRepository,
            LocationRepository locationRepository,
            DonorProfileRepository donorProfileRepository
    ) {
        this.donorRepository = donorRepository;
        this.locationRepository = locationRepository;
        this.donorProfileRepository = donorProfileRepository;
    }

    @Transactional
    public DonorResponse createDonor(CreateDonorRequest req) {
        if (donorRepository.existsByEmail(req.email())) {
            throw new BadRequestException("Email already registered: " + req.email());
        }

        Location village = locationRepository.findByCode(req.villageCode())
                .orElseThrow(() -> new NotFoundException("Village not found: " + req.villageCode()));
        if (village.getType() != Location.ELocationType.VILLAGE) {
            throw new BadRequestException("Location code is not a VILLAGE: " + req.villageCode());
        }

        Donor d = new Donor();
        d.setFirstName(req.firstName());
        d.setLastName(req.lastName());
        d.setEmail(req.email());
        d.setPhone(req.phone());
        d.setBloodType(req.bloodType());
        d.setVillageLocation(village);

        return toResponse(donorRepository.save(d));
    }

    @Transactional
    public DonorProfile createProfile(CreateDonorProfileRequest req) {
        Donor donor = donorRepository.findById(req.donorId())
                .orElseThrow(() -> new NotFoundException("Donor not found: " + req.donorId()));

        if (donor.getDonorProfile() != null) {
            throw new BadRequestException("Donor already has a profile: " + req.donorId());
        }

        DonorProfile profile = new DonorProfile();
        profile.setDonor(donor);
        profile.setMedicalHistory(req.medicalHistory());
        profile.setWeightKg(req.weightKg());
        profile.setLastDonationDate(req.lastDonationDate());
        return donorProfileRepository.save(profile);
    }

    @Transactional(readOnly = true)
    public Page<DonorResponse> listDonors(Pageable pageable) {
        return donorRepository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<DonorResponse> listDonorsByProvince(String provinceCode, String provinceName, Pageable pageable) {
        if ((provinceCode == null || provinceCode.isBlank()) && (provinceName == null || provinceName.isBlank())) {
            throw new BadRequestException("Provide provinceCode or provinceName");
        }
        String code = (provinceCode == null || provinceCode.isBlank()) ? null : provinceCode;
        String name = (provinceName == null || provinceName.isBlank()) ? null : provinceName;
        return donorRepository.findByProvinceCodeOrName(code, name, pageable).map(this::toResponse);
    }

    private DonorResponse toResponse(Donor d) {
        Location village = d.getVillageLocation();
        Location cell = village.getParent();
        Location sector = cell == null ? null : cell.getParent();
        Location district = sector == null ? null : sector.getParent();
        Location province = district == null ? null : district.getParent();

        return new DonorResponse(
                d.getId(),
                d.getFirstName(),
                d.getLastName(),
                d.getEmail(),
                d.getPhone(),
                d.getBloodType(),
                village.getCode(),
                village.getName(),
                cell == null ? null : cell.getCode(),
                sector == null ? null : sector.getCode(),
                district == null ? null : district.getCode(),
                province == null ? null : province.getCode(),
                province == null ? null : province.getName()
        );
    }
}

