package com.code.gen;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CodeGenService {

    @Autowired
    private GeneratedCodeRepository generatedCodeRepository;

    public String generateCode(Long templateId, Map<String, String> fieldValues) {
        try {
            // Fetch template from TEMPLATE-SERVICE
            RestTemplate restTemplate = new RestTemplate();
            String templateUrl = "http://localhost:8082/api/templates/" + templateId;
            String templateResponse = restTemplate.getForObject(templateUrl, String.class);

            // Parse response using ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(templateResponse);

            String templateContent = jsonNode.get("content").asText();

            // Replace placeholders in template with user-provided values
            for (Map.Entry<String, String> entry : fieldValues.entrySet()) {
                templateContent = templateContent.replace("{{" + entry.getKey() + "}}", entry.getValue());
            }

            return templateContent;

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate code: " + e.getMessage(), e);
        }
    }

    public GeneratedCode saveGeneratedCode(Long projectId, String templateName, String generatedCode, String language, String codeName) {
        GeneratedCode code = new GeneratedCode();
        code.setProjectId(projectId);
        code.setTemplateName(templateName);
        code.setGeneratedCode(generatedCode);
        code.setLanguage(language);
        code.setCodeName(codeName);
        return generatedCodeRepository.save(code);
    }



}
