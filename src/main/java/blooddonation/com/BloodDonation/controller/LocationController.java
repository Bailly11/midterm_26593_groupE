package blooddonation.com.BloodDonation.controller;

import blooddonation.com.BloodDonation.controller.dto.LocationDto;
import blooddonation.com.BloodDonation.domain.Location;
import blooddonation.com.BloodDonation.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<LocationDto.LocationResponse> create(
            @Valid @RequestBody LocationDto.CreateLocationRequest req
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.create(req));
    }

    @GetMapping
    public ResponseEntity<List<LocationDto.LocationResponse>> listByType(
            @RequestParam Location.ELocationType type
    ) {
        return ResponseEntity.ok(locationService.listByType(type));
    }
}

