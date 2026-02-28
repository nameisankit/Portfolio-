package in.ankitparmar.portfolio.controller;

import in.ankitparmar.portfolio.model.Project;
import in.ankitparmar.portfolio.repository.ProjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/projects")
public class AdminProjectController {

    private final ProjectRepository projectRepository;

    public AdminProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("projects", projectRepository.findAll());
        return "admin/projects-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("project", new Project());
        return "admin/project-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Project project) {
        projectRepository.save(project);
        return "redirect:/admin/projects";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project Id: " + id));
        model.addAttribute("project", project);
        return "admin/project-form";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        projectRepository.deleteById(id);
        return "redirect:/admin/projects";
    }
}
