package blooddonation.com.BloodDonation.controller;

import blooddonation.com.BloodDonation.controller.dto.CreateBloodRequestRequest;
import blooddonation.com.BloodDonation.domain.BloodRequest;
import blooddonation.com.BloodDonation.service.BloodRequestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/blood-requests")
public class BloodRequestController {

    private final BloodRequestService bloodRequestService;

    public BloodRequestController(BloodRequestService bloodRequestService) {
        this.bloodRequestService = bloodRequestService;
    }

    @PostMapping
    public ResponseEntity<BloodRequest> create(@Valid @RequestBody CreateBloodRequestRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bloodRequestService.create(req));
    }

    @GetMapping
    public ResponseEntity<List<BloodRequest>> list() {
        return ResponseEntity.ok(bloodRequestService.list());
    }
}

