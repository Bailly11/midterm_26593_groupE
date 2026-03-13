package blooddonation.com.BloodDonation.controller.dto;

import blooddonation.com.BloodDonation.domain.Location;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LocationDto {

    public record CreateLocationRequest(
            @NotBlank String code,
            @NotBlank String name,
            @NotNull Location.ELocationType type,
            String parentCode
    ) {
    }

    public record LocationResponse(
            Long id,
            String code,
            String name,
            Location.ELocationType type,
            Long parentId,
            String parentCode
    ) {
    }
}

