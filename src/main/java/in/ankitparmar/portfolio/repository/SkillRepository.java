package in.ankitparmar.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ankitparmar.portfolio.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}

