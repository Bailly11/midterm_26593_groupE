package blooddonation.com.BloodDonation.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateDonationRecordRequest(
        @NotNull Long donorId,
        @NotNull Long bloodBankId,
        @NotNull LocalDateTime donationDate,
        @NotNull @Min(1) Integer units
) {
}

