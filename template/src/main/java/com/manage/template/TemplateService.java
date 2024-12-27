package com.manage.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    public Template saveTemplate(Template template) {
        if (template.getMetadata() == null || template.getMetadata().isBlank()) {
            throw new IllegalArgumentException("Metadata cannot be null or empty.");
        }
        return templateRepository.save(template);
    }


    public Optional<Template> getTemplateById(Long id) {
        return templateRepository.findById(id);
    }

    public List<Template> getAllTemplates() {
        return templateRepository.findAll();
    }

    public List<Template> getTemplatesByLanguage(String language) {
        return templateRepository.findByLanguage(language);
    }

    public void deleteTemplate(Long id) {
        templateRepository.deleteById(id);
    }
}
