package com.practice.demo.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Long id) {
        super("Course with id=" + id + " was not found");
    }
}
