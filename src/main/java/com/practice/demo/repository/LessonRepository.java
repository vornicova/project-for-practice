package com.practice.demo.repository;

import com.practice.demo.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<LessonEntity, Long> {
}
