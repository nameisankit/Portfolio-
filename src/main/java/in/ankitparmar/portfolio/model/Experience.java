package in.ankitparmar.portfolio.model;

import jakarta.persistence.*;

@Entity
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // e.g. "March 2024 – Present"
    private String dateRange;

    // e.g. "Mentor - Java | React | Full Stack"
    private String title;

    // Timeline description
    @Column(length = 2000)
    private String description;

    // Company / org name
private String organization;

// Location line: city, state, country
private String location;

// Responsibilities as multi-line text (each line -> bullet point)
@Column(length = 2000)
private String responsibilities;

// Career growth – each line: Date|Text
@Column(length = 2000)
private String careerGrowth;

// Skills / tools – comma separated: "React.js, Node.js, MongoDB"
private String skillTags;


    // e.g. "fa-solid fa-person-chalkboard"
    private String iconClass;

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getCareerGrowth() {
        return careerGrowth;
    }

    public void setCareerGrowth(String careerGrowth) {
        this.careerGrowth = careerGrowth;
    }

    public String getSkillTags() {
        return skillTags;
    }

    public void setSkillTags(String skillTags) {
        this.skillTags = skillTags;
    }

    // for ordering in UI (1,2,3…)
    private Integer sortOrder;


    
    // getters / setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}
