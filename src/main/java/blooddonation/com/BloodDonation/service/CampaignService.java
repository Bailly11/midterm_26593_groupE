package blooddonation.com.BloodDonation.service;

import blooddonation.com.BloodDonation.controller.dto.CreateCampaignRequest;
import blooddonation.com.BloodDonation.controller.dto.JoinCampaignRequest;
import blooddonation.com.BloodDonation.domain.Campaign;
import blooddonation.com.BloodDonation.domain.Donor;
import blooddonation.com.BloodDonation.repository.CampaignRepository;
import blooddonation.com.BloodDonation.repository.DonorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final DonorRepository donorRepository;

    public CampaignService(CampaignRepository campaignRepository, DonorRepository donorRepository) {
        this.campaignRepository = campaignRepository;
        this.donorRepository = donorRepository;
    }

    @Transactional
    public Campaign createCampaign(CreateCampaignRequest req) {
        if (campaignRepository.existsByName(req.name())) {
            throw new BadRequestException("Campaign name already exists: " + req.name());
        }
        Campaign c = new Campaign();
        c.setName(req.name());
        c.setStartDate(req.startDate());
        c.setEndDate(req.endDate());
        return campaignRepository.save(c);
    }

    @Transactional
    public void joinCampaign(JoinCampaignRequest req) {
        Donor donor = donorRepository.findById(req.donorId())
                .orElseThrow(() -> new NotFoundException("Donor not found: " + req.donorId()));
        Campaign campaign = campaignRepository.findById(req.campaignId())
                .orElseThrow(() -> new NotFoundException("Campaign not found: " + req.campaignId()));
        donor.getCampaigns().add(campaign);
        donorRepository.save(donor);
    }

    @Transactional(readOnly = true)
    public List<Campaign> listCampaigns() {
        return campaignRepository.findAll();
    }
}

