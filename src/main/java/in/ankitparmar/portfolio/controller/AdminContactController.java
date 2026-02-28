package in.ankitparmar.portfolio.controller;

import in.ankitparmar.portfolio.model.ContactInfo;
import in.ankitparmar.portfolio.repository.ContactInfoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/contact")
public class AdminContactController {

    private final ContactInfoRepository contactInfoRepository;

    public AdminContactController(ContactInfoRepository contactInfoRepository) {
        this.contactInfoRepository = contactInfoRepository;
    }

    @GetMapping
    public String editContact(Model model) {
        ContactInfo info = contactInfoRepository.findAll()
                .stream().findFirst().orElse(new ContactInfo());
        model.addAttribute("contactInfo", info);
        return "admin/contact-form";
    }

    @PostMapping("/save")
    public String saveContact(@ModelAttribute ContactInfo contactInfo) {
        if (contactInfo.getId() == null && contactInfoRepository.count() > 0) {
            ContactInfo existing = contactInfoRepository.findAll().get(0);
            contactInfo.setId(existing.getId());
        }
        contactInfoRepository.save(contactInfo);
        return "redirect:/admin/contact";
    }
}
