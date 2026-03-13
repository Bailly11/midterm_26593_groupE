package blooddonation.com.BloodDonation.controller.dto;

import blooddonation.com.BloodDonation.domain.EBloodType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateDonorRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @Email @NotBlank String email,
        @NotBlank String phone,
        @NotNull EBloodType bloodType,
        @NotBlank String villageCode
) {
}

