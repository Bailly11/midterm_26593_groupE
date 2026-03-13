package blooddonation.com.BloodDonation.controller.dto;

import blooddonation.com.BloodDonation.domain.EBloodType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateBloodRequestRequest(
        @NotNull Long bloodBankId,
        @NotNull EBloodType bloodType,
        @NotNull @Min(1) Integer units,
        @NotNull LocalDateTime requestDate
) {
}

