package in.ankitparmar.portfolio.controller;

import in.ankitparmar.portfolio.model.Experience;
import in.ankitparmar.portfolio.repository.ExperienceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/experience")
public class AdminExperienceController {

    private final ExperienceRepository experienceRepository;

    public AdminExperienceController(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("experiences", experienceRepository.findAllByOrderBySortOrderAsc());
        return "admin/experience-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        Experience exp = new Experience();
        exp.setIconClass("fa-solid fa-briefcase");
        exp.setSortOrder(1);
        model.addAttribute("experience", exp);
        return "admin/experience-form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Experience exp = experienceRepository.findById(id).orElseThrow();
        model.addAttribute("experience", exp);
        return "admin/experience-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Experience experience) {
        experienceRepository.save(experience);
        return "redirect:/admin/experience";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        experienceRepository.deleteById(id);
        return "redirect:/admin/experience";
    }
}
