package com.practice.demo.testdata;

import com.practice.demo.entity.CourseEntity;

public final class CourseEntityFactory {

    private CourseEntityFactory() {
    }

    public static CourseEntity defaultCourse() {
        return CourseEntity.builder()
                .id(1L)
                .title("Java")
                .description("Basics")
                .isActive(true)
                .build();
    }

    public static CourseEntity updatedCourse() {
        return CourseEntity.builder()
                .id(1L)
                .title("Advanced Java")
                .description("Advanced topics")
                .isActive(false)
                .build();
    }
}
