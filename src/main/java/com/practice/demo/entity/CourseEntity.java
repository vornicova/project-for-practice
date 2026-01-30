package com.practice.demo.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;
}
