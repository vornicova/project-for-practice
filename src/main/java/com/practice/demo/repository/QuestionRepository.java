package com.practice.demo.repository;

import com.practice.demo.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository <QuestionEntity,Long> {
}
