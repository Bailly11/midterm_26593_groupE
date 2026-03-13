package blooddonation.com.BloodDonation.controller.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CreateCampaignRequest(
        @NotBlank String name,
        LocalDate startDate,
        LocalDate endDate
) {
}

