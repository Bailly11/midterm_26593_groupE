package blooddonation.com.BloodDonation.repository;

import blooddonation.com.BloodDonation.domain.Donor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DonorRepository extends JpaRepository<Donor, Long> {
    boolean existsByEmail(String email);

    @Query("""
            select d from Donor d
            join d.villageLocation v
            join v.parent c
            join c.parent s
            join s.parent dist
            join dist.parent prov
            where (:provinceCode is not null and prov.code = :provinceCode)
               or (:provinceName is not null and lower(prov.name) = lower(:provinceName))
            """)
    Page<Donor> findByProvinceCodeOrName(
            @Param("provinceCode") String provinceCode,
            @Param("provinceName") String provinceName,
            Pageable pageable
    );
}

