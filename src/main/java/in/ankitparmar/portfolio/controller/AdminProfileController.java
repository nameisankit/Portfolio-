package in.ankitparmar.portfolio.controller;

import in.ankitparmar.portfolio.model.Profile;
import in.ankitparmar.portfolio.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/admin/profile")
public class AdminProfileController {

    private final ProfileRepository profileRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;  // e.g. "uploads"

    public AdminProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @GetMapping
    public String editProfile(Model model) {
        Profile profile = profileRepository.findAll()
                .stream().findFirst().orElse(new Profile());
        model.addAttribute("profile", profile);
        return "admin/profile-form";
    }

    @PostMapping("/save")
    public String saveProfile(@ModelAttribute Profile profile,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              @RequestParam(value = "resumeFile", required = false) MultipartFile resumeFile
    ) throws IOException {

        // --------- ensure single record + preserve old image/resume ----------
        Profile existing = null;

        if (profile.getId() != null) {
            existing = profileRepository.findById(profile.getId()).orElse(null);
        } else if (profileRepository.count() > 0) {
            existing = profileRepository.findAll().get(0);
            profile.setId(existing.getId());
        }

        if (existing != null) {
            if (profile.getProfileImageUrl() == null) {
                profile.setProfileImageUrl(existing.getProfileImageUrl());
            }
            if (profile.getResumeUrl() == null) {
                profile.setResumeUrl(existing.getResumeUrl());
            }
        }

        // --------- profile image upload ----------
        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());

            Path uploadPath = Paths.get(uploadDir); // e.g. /uploads
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            profile.setProfileImageUrl("/uploads/" + fileName);
        }

        // --------- resume PDF upload ----------
        if (resumeFile != null && !resumeFile.isEmpty()) {
            String fileName = "resume_" + System.currentTimeMillis() + ".pdf";

            Path resumePath = Paths.get(uploadDir, "resume");  // uploads/resume
            if (!Files.exists(resumePath)) {
                Files.createDirectories(resumePath);
            }

            Files.copy(resumeFile.getInputStream(),
                    resumePath.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);

            profile.setResumeUrl("/uploads/resume/" + fileName);
        }

        profileRepository.save(profile);
        return "redirect:/admin/profile";
    }
}
