package in.ankitparmar.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.ankitparmar.portfolio.model.Skill;
import in.ankitparmar.portfolio.repository.SkillRepository;


    @Controller
@RequestMapping("/admin/skills")
public class SkillAdminController {

    private final SkillRepository skillRepository;

    public SkillAdminController(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @GetMapping
    public String listSkills(Model model) {
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute("skill", new Skill());
          return "admin/admin-skills";   // ⭐ FIX
    }

    @GetMapping("/edit/{id}")
    public String editSkill(@PathVariable Long id, Model model) {
        Skill skill = skillRepository.findById(id).orElseThrow();
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute("skill", skill);
          return "admin/admin-skills";   // ⭐ FIX
    }

    @PostMapping("/save")
    public String saveSkill(@ModelAttribute Skill skill) {
        skillRepository.save(skill);
        return "redirect:/admin/skills";
    }

    @GetMapping("/delete/{id}")
    public String deleteSkill(@PathVariable Long id) {
        skillRepository.deleteById(id);
        return "redirect:/admin/skills";
    }
}
