package in.ankitparmar.portfolio.repository;

import in.ankitparmar.portfolio.model.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}
