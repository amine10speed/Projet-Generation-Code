package com.code.gen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/codegen")
public class CodeGenController {

    @Autowired
    private CodeGenService codeGenService;

    @Autowired
    private GeneratedCodeRepository generatedCodeRepository;

    @PostMapping("/{templateId}")
    public ResponseEntity<GeneratedCode> generateAndSaveCode(
            @PathVariable Long templateId,
            @RequestParam Long projectId,
            @RequestBody Map<String, String> fieldValues) {

        String generatedCode = codeGenService.generateCode(templateId, fieldValues);
        String templateName = fieldValues.getOrDefault("templateName", "Unknown Template");
        String language = fieldValues.getOrDefault("language", "Unknown Language");
        String codeName = fieldValues.getOrDefault("codeName", "Unnamed Code"); // Retrieve codeName

        // Save the generated code
        GeneratedCode savedCode = codeGenService.saveGeneratedCode(projectId, templateName, generatedCode, language, codeName);

        // Notify PROJECT-SERVICE
        RestTemplate restTemplate = new RestTemplate();
        String projectServiceUrl = "http://localhost:8084/api/projects/" + projectId + "/add-code";
        restTemplate.postForObject(projectServiceUrl, Map.of("generatedCodeId", savedCode.getId()), Void.class);

        return ResponseEntity.ok(savedCode);
    }


    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<GeneratedCode>> getGeneratedCodesByProjectId(@PathVariable Long projectId) {
        List<GeneratedCode> generatedCodes = generatedCodeRepository.findByProjectId(projectId);
        return ResponseEntity.ok(generatedCodes);
    }


    @GetMapping("/file/{fileId}")
    public ResponseEntity<GeneratedCode> getGeneratedCodeDetails(@PathVariable Long fileId) {
        return generatedCodeRepository.findById(fileId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
