package in.ankitparmar.portfolio.controller;

import in.ankitparmar.portfolio.model.Profile;
import in.ankitparmar.portfolio.repository.ProfileRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class ResumeController {

    private final ProfileRepository profileRepository;

    public ResumeController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @GetMapping("/resume/view")
    public ResponseEntity<byte[]> viewResume() {
        Profile profile = profileRepository.findAll()
                .stream().findFirst().orElse(null);

        if (profile == null || profile.getResumeUrl() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        try {
            RestTemplate rest = new RestTemplate();
            ResponseEntity<byte[]> response =
                    rest.getForEntity(profile.getResumeUrl(), byte[].class);

            if (response.getBody() == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(
                    ContentDisposition.inline()
                            .filename("Ankit_Parmar_Resume.pdf")
                            .build()
            );

            return new ResponseEntity<>(response.getBody(), headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
