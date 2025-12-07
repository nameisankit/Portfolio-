package in.ankitparmar.portfolio.controller;

import in.ankitparmar.portfolio.model.Profile;
import in.ankitparmar.portfolio.repository.ProfileRepository;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.InputStream;
import java.net.URL;

@Controller
public class ResumeController {

    private final ProfileRepository profileRepository;

    public ResumeController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @GetMapping("/resume/view")
    public ResponseEntity<byte[]> viewResume() {
        // ek hi profile record hai, wahi le lete hain
        Profile profile = profileRepository.findAll()
                .stream().findFirst().orElse(null);

        if (profile == null || profile.getResumeUrl() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        try {
            // Cloudinary URL se bytes read karo
            URL url = new URL(profile.getResumeUrl());

            byte[] data;
            try (InputStream in = url.openStream()) {
                data = in.readAllBytes();
            }

            // proper PDF headers set karo
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(
                    ContentDisposition.inline()
                            .filename("Ankit_Parmar_Resume.pdf")
                            .build()
            );

            return new ResponseEntity<>(data, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
