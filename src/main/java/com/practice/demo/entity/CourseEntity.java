package com.practice.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<LessonEntity> lessons = new ArrayList<>();

    @Column(nullable = false)
    private String title;

    private String description;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;
}
