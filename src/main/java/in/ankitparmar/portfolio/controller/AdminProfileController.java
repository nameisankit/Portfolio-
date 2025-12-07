package in.ankitparmar.portfolio.controller;

import in.ankitparmar.portfolio.model.Profile;
import in.ankitparmar.portfolio.repository.ProfileRepository;
import in.ankitparmar.portfolio.service.FileUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/profile")
public class AdminProfileController {

    private final ProfileRepository profileRepository;
    private final FileUploadService fileUploadService;

    public AdminProfileController(ProfileRepository profileRepository,
                                  FileUploadService fileUploadService) {
        this.profileRepository = profileRepository;
        this.fileUploadService = fileUploadService;
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
                              @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                              @RequestParam(value = "resumeFile", required = false) MultipartFile resumeFile,
                              @RequestParam(value = "aboutImageFile", required = false) MultipartFile aboutImageFile
    ) throws Exception {

        // ek hi record rakho + purani image/resume/aboutImage preserve karo
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
            if (profile.getAboutImageUrl() == null) {
                profile.setAboutImageUrl(existing.getAboutImageUrl());
            }
        }

        // PROFILE IMAGE → Cloudinary
        if (imageFile != null && !imageFile.isEmpty()) {
            String imgUrl = fileUploadService.uploadImage(imageFile);
            profile.setProfileImageUrl(imgUrl);
        }

        // ABOUT IMAGE → Cloudinary (alag folder)
        if (aboutImageFile != null && !aboutImageFile.isEmpty()) {
            String aboutImgUrl = fileUploadService.uploadAboutImage(aboutImageFile);
            profile.setAboutImageUrl(aboutImgUrl);
        }

        // RESUME PDF → Cloudinary
        if (resumeFile != null && !resumeFile.isEmpty()) {
            String resumeUrl = fileUploadService.uploadResume(resumeFile);
            profile.setResumeUrl(resumeUrl);
        }

        profileRepository.save(profile);
        return "redirect:/admin/profile";
    }
}
