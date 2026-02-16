package com.practice.demo.repository;

import com.practice.demo.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    @Query("""
    select distinct c
    from CourseEntity c
    left join fetch c.lessons
""")
    List<CourseEntity> findAllWithActiveLessons();
}
