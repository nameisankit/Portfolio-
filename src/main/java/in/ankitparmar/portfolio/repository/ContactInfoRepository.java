package in.ankitparmar.portfolio.repository;

import in.ankitparmar.portfolio.model.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {
}
