package com.practice.demo.exception;

public class LessonNotFoundException extends RuntimeException {
    public LessonNotFoundException(Long id) {
        super("Lesson with id " + id + " not found");
    }
}
