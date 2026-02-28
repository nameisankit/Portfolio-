package in.ankitparmar.portfolio.model;

import jakarta.persistence.*;

@Entity
public class ContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;
    private String email;
    private String linkedinUrl;
    private String telegramUrl;
    private String instagramUrl;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getLinkedinUrl() {
        return linkedinUrl;
    }
    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }
    public String getTelegramUrl() {
        return telegramUrl;
    }
    public void setTelegramUrl(String telegramUrl) {
        this.telegramUrl = telegramUrl;
    }
    public String getInstagramUrl() {
        return instagramUrl;
    }
    public void setInstagramUrl(String instagramUrl) {
        this.instagramUrl = instagramUrl;
    }
    public String getTwitterUrl() {
        return twitterUrl;
    }
    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }
    public String getGithubUrl() {
        return githubUrl;
    }
    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }
    private String twitterUrl;
    private String githubUrl;

    // getters/setters...
}
