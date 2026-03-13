package blooddonation.com.BloodDonation.controller.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateDonorProfileRequest(
        @NotNull Long donorId,
        String medicalHistory,
        Double weightKg,
        LocalDate lastDonationDate
) {
}

