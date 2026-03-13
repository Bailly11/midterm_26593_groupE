package blooddonation.com.BloodDonation.controller;

import blooddonation.com.BloodDonation.controller.dto.CreateBloodBankRequest;
import blooddonation.com.BloodDonation.domain.BloodBank;
import blooddonation.com.BloodDonation.service.BloodBankService;
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
@RequestMapping("/api/blood-banks")
public class BloodBankController {

    private final BloodBankService bloodBankService;

    public BloodBankController(BloodBankService bloodBankService) {
        this.bloodBankService = bloodBankService;
    }

    @PostMapping
    public ResponseEntity<BloodBank> create(@Valid @RequestBody CreateBloodBankRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bloodBankService.create(req));
    }

    @GetMapping
    public ResponseEntity<List<BloodBank>> list() {
        return ResponseEntity.ok(bloodBankService.list());
    }
}

