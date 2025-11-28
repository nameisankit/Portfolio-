package in.ankitparmar.portfolio.repository;

import in.ankitparmar.portfolio.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
