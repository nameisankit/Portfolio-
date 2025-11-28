package in.ankitparmar.portfolio.controller;

import in.ankitparmar.portfolio.model.ContactInfo;
import in.ankitparmar.portfolio.model.Profile;
import in.ankitparmar.portfolio.model.Project;
import in.ankitparmar.portfolio.model.Achievement;
import in.ankitparmar.portfolio.repository.ProjectRepository;
import in.ankitparmar.portfolio.repository.SkillRepository;
import in.ankitparmar.portfolio.repository.ProfileRepository;
import in.ankitparmar.portfolio.repository.AchievementRepository;
import in.ankitparmar.portfolio.repository.ContactInfoRepository;
import in.ankitparmar.portfolio.repository.ExperienceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private final ProfileRepository profileRepository;
    private final ProjectRepository projectRepository;
    private final AchievementRepository achievementRepository;
    private final ContactInfoRepository contactInfoRepository;
    private final ExperienceRepository experienceRepository;
    private final SkillRepository skillRepository;   // ⭐ NEW

    public HomeController(ProfileRepository profileRepository,
                          ProjectRepository projectRepository,
                          AchievementRepository achievementRepository,
                          ContactInfoRepository contactInfoRepository,
                          ExperienceRepository experienceRepository,
                          SkillRepository skillRepository) { // ⭐ NEW
        this.profileRepository = profileRepository;
        this.projectRepository = projectRepository;
        this.achievementRepository = achievementRepository;
        this.contactInfoRepository = contactInfoRepository;
        this.experienceRepository = experienceRepository;
        this.skillRepository = skillRepository; // ⭐ NEW
    }

    @GetMapping({"", "/", "/home"})
    public String showHomePage(Model model) {

        // ===== Profile =====
// ===== Profile =====
Profile profile = profileRepository.findAll()
        .stream()
        .findFirst()
        .orElse(new Profile());   // <- yahan null ki jagah NEW Profile()

model.addAttribute("profile", profile);


        // ⭐ Skills (About section ke liye)
        model.addAttribute("skills", skillRepository.findAll());

        // ===== Projects -> 3-3 ke slides =====
        List<Project> projects = projectRepository.findAll();
        List<List<Project>> projectSlides = new ArrayList<>();
        int projectChunkSize = 3; // 3 cards per slide

        for (int i = 0; i < projects.size(); i += projectChunkSize) {
            projectSlides.add(
                    projects.subList(i, Math.min(i + projectChunkSize, projects.size()))
            );
        }

        model.addAttribute("projectSlides", projectSlides);

        // ===== Achievements -> 3-3 ke slides =====
        List<Achievement> achievements = achievementRepository.findAll();
        List<List<Achievement>> achievementSlides = new ArrayList<>();
        int achievementChunkSize = 3;

        for (int i = 0; i < achievements.size(); i += achievementChunkSize) {
            achievementSlides.add(
                    achievements.subList(i, Math.min(i + achievementChunkSize, achievements.size()))
            );
        }

        model.addAttribute("achievementSlides", achievementSlides);

        // ===== Experience =====
        model.addAttribute("experiences", experienceRepository.findAllByOrderBySortOrderAsc());

        // ===== Contact info =====
        ContactInfo contactInfo = contactInfoRepository.findAll()
                .stream()
                .findFirst()
                .orElse(new ContactInfo());
        model.addAttribute("contactInfo", contactInfo);

        return "home";
    }
}
