package blooddonation.com.BloodDonation.controller.dto;

import blooddonation.com.BloodDonation.domain.EBloodType;

public record DonorResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        EBloodType bloodType,
        String villageCode,
        String villageName,
        String cellCode,
        String sectorCode,
        String districtCode,
        String provinceCode,
        String provinceName
) {
}

