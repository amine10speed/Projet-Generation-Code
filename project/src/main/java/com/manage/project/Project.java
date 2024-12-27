package com.manage.project;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // Reference to the user who owns this project

    private String projectName;

    @ElementCollection
    private List<Long> generatedCodeIds; // List of IDs referencing generated codes

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Long> getGeneratedCodeIds() {
        return generatedCodeIds;
    }

    public void setGeneratedCodeIds(List<Long> generatedCodeIds) {
        this.generatedCodeIds = generatedCodeIds;
    }
}
