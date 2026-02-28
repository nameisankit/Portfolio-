package in.ankitparmar.portfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;       // e.g. "Java"
    private String iconClass;  // e.g. "fab fa-java"
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIconClass() {
        return iconClass;
    }
    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    // getters & setters
}
    
