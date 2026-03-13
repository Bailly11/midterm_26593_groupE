package blooddonation.com.BloodDonation.controller;

import blooddonation.com.BloodDonation.controller.dto.CreateDonorProfileRequest;
import blooddonation.com.BloodDonation.controller.dto.CreateDonorRequest;
import blooddonation.com.BloodDonation.controller.dto.DonorResponse;
import blooddonation.com.BloodDonation.domain.DonorProfile;
import blooddonation.com.BloodDonation.service.DonorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/donors")
public class DonorController {

    private final DonorService donorService;

    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @PostMapping
    public ResponseEntity<DonorResponse> create(@Valid @RequestBody CreateDonorRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(donorService.createDonor(req));
    }

    @PostMapping("/profile")
    public ResponseEntity<DonorProfile> createProfile(@Valid @RequestBody CreateDonorProfileRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(donorService.createProfile(req));
    }

    @GetMapping
    public ResponseEntity<Page<DonorResponse>> list(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(donorService.listDonors(pageable));
    }

    @GetMapping("/by-province")
    public ResponseEntity<Page<DonorResponse>> byProvince(
            @RequestParam(required = false) String provinceCode,
            @RequestParam(required = false) String provinceName,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return ResponseEntity.ok(donorService.listDonorsByProvince(provinceCode, provinceName, pageable));
    }
}

