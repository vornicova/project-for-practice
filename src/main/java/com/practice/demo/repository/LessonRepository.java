package com.practice.demo.repository;

import com.practice.demo.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<LessonEntity, Long> {
    List<LessonEntity> findByCourseIdAndIsActiveTrue(Long courseId);

    List<LessonEntity> findByCourseIdInAndIsActiveTrue(List<Long> courseIds);
}
