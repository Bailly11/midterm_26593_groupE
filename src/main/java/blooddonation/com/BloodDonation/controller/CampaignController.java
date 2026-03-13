package blooddonation.com.BloodDonation.controller;

import blooddonation.com.BloodDonation.controller.dto.CreateCampaignRequest;
import blooddonation.com.BloodDonation.controller.dto.JoinCampaignRequest;
import blooddonation.com.BloodDonation.domain.Campaign;
import blooddonation.com.BloodDonation.service.CampaignService;
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
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping
    public ResponseEntity<Campaign> create(@Valid @RequestBody CreateCampaignRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(campaignService.createCampaign(req));
    }

    @PostMapping("/join")
    public ResponseEntity<Void> join(@Valid @RequestBody JoinCampaignRequest req) {
        campaignService.joinCampaign(req);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Campaign>> list() {
        return ResponseEntity.ok(campaignService.listCampaigns());
    }
}

