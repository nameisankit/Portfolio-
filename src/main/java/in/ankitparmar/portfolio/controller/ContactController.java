package in.ankitparmar.portfolio.controller;

import in.ankitparmar.portfolio.service.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {

    private final EmailService emailService;

    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/contact/send")
    public String sendContact(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String message,
            RedirectAttributes redirectAttributes
    ) {
        // Email bhejo
        emailService.sendContactEmail(name, email, message);

        // UI ke liye success message
        redirectAttributes.addFlashAttribute("successMessage", "Message sent successfully!");
        return "redirect:/#contact";
    }
}
