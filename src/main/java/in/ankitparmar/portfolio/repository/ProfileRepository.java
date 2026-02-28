package in.ankitparmar.portfolio.repository;

import in.ankitparmar.portfolio.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
