package in.ankitparmar.portfolio.model;

import jakarta.persistence.*;

@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;          // "Ankit Parmar"
    private String title;             // "Web Developer"

    @Column(length = 2000)
    private String shortBio;          // hero section ka text

    @Column(length = 4000)
    private String aboutText;         // About Me ka main paragraph

    private String profileImageUrl;   // "/uploads/profile.png" etc.

    private String resumeUrl;

    private String roles;  // comma separated saved

    private String aboutImageUrl;

public String getAboutImageUrl() { 
    return aboutImageUrl; }

public void setAboutImageUrl(String aboutImageUrl) { 
    this.aboutImageUrl = aboutImageUrl;
 }




    public String getResumeUrl() {
    return resumeUrl;
}

public void setResumeUrl(String resumeUrl) {
    this.resumeUrl = resumeUrl;
}




    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getShortBio() { return shortBio; }
    public void setShortBio(String shortBio) { this.shortBio = shortBio; }

    public String getAboutText() { return aboutText; }
    public void setAboutText(String aboutText) { this.aboutText = aboutText; }

    public String getProfileImageUrl() { return profileImageUrl; }
    public void setProfileImageUrl(String profileImageUrl) { this.profileImageUrl = profileImageUrl; }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
