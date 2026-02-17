package com.practice.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String content;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;
}
