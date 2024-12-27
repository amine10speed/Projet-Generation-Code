package com.manage.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Map<String, String> payload) {
        Long userId = Long.parseLong(payload.get("userId"));
        String projectName = payload.get("projectName");
        return ResponseEntity.ok(projectService.createProject(userId, projectName));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Project>> getProjectsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(projectService.getProjectsByUserId(userId));
    }

    @GetMapping("/details/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.getProjectById(projectId));
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{projectId}/add-code")
    public ResponseEntity<Project> addGeneratedCode(
            @PathVariable Long projectId,
            @RequestBody Map<String, Long> payload) {
        Long generatedCodeId = payload.get("generatedCodeId");
        return ResponseEntity.ok(projectService.addGeneratedCodeToProject(projectId, generatedCodeId));
    }

}
