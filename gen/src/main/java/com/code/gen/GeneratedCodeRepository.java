package com.code.gen;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneratedCodeRepository extends JpaRepository<GeneratedCode, Long> {
    List<GeneratedCode> findByProjectId(Long projectId);
}
