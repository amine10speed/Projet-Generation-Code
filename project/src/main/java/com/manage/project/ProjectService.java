package com.manage.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Long userId, String projectName) {
        Project project = new Project();
        project.setUserId(userId);
        project.setProjectName(projectName);
        return projectRepository.save(project);
    }

    public List<Project> getProjectsByUserId(Long userId) {
        return projectRepository.findByUserId(userId);
    }

    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    public Project addGeneratedCodeToProject(Long projectId, Long generatedCodeId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.getGeneratedCodeIds().add(generatedCodeId);
        return projectRepository.save(project);
    }

}
