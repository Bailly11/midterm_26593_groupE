package blooddonation.com.BloodDonation.controller.dto;

import jakarta.validation.constraints.NotNull;

public record JoinCampaignRequest(
        @NotNull Long donorId,
        @NotNull Long campaignId
) {
}

