package blooddonation.com.BloodDonation.service;

import blooddonation.com.BloodDonation.controller.dto.LocationDto;
import blooddonation.com.BloodDonation.domain.Location;
import blooddonation.com.BloodDonation.repository.LocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Transactional
    public LocationDto.LocationResponse create(LocationDto.CreateLocationRequest req) {
        if (locationRepository.existsByCode(req.code())) {
            throw new BadRequestException("Location code already exists: " + req.code());
        }

        Location parent = null;
        if (req.parentCode() != null && !req.parentCode().isBlank()) {
            parent = locationRepository.findByCode(req.parentCode())
                    .orElseThrow(() -> new NotFoundException("Parent location not found: " + req.parentCode()));
        }

        if (req.type() == Location.ELocationType.PROVINCE && parent != null) {
            throw new BadRequestException("Province cannot have a parent");
        }
        if (req.type() != Location.ELocationType.PROVINCE && parent == null) {
            throw new BadRequestException(req.type() + " must have a parentCode");
        }

        Location l = new Location();
        l.setCode(req.code());
        l.setName(req.name());
        l.setType(req.type());
        l.setParent(parent);
        return toResponse(locationRepository.save(l));
    }

    @Transactional(readOnly = true)
    public List<LocationDto.LocationResponse> listByType(Location.ELocationType type) {
        return locationRepository.findByType(type).stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public Location getByCode(String code) {
        return locationRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Location not found: " + code));
    }

    private LocationDto.LocationResponse toResponse(Location l) {
        Location p = l.getParent();
        return new LocationDto.LocationResponse(
                l.getId(),
                l.getCode(),
                l.getName(),
                l.getType(),
                p == null ? null : p.getId(),
                p == null ? null : p.getCode()
        );
    }
}

