package blooddonation.com.BloodDonation.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateBloodBankRequest(
        @NotBlank String name,
        String addressLine,
        @NotBlank String villageCode
) {
}

