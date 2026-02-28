package in.ankitparmar.portfolio.controller;

import in.ankitparmar.portfolio.model.Achievement;
import in.ankitparmar.portfolio.repository.AchievementRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/achievements")
public class AdminAchievementController {

    private final AchievementRepository achievementRepository;

    public AdminAchievementController(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("achievements", achievementRepository.findAll());
        return "admin/achievements-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("achievement", new Achievement());
        return "admin/achievement-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Achievement achievement) {
        achievementRepository.save(achievement);
        return "redirect:/admin/achievements";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Achievement a = achievementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid achievement id " + id));
        model.addAttribute("achievement", a);
        return "admin/achievement-form";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        achievementRepository.deleteById(id);
        return "redirect:/admin/achievements";
    }
}
