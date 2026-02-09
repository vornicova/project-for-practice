package com.practice.demo.testdata;

public final class CourseJsonFactory {

    private CourseJsonFactory() {
    }

    public static String createCourseJson() {
        return """
                {
                  "title": "Java",
                  "description": "Basics"
                }
                """;
    }

    public static String updateCourseJson() {
        return """
                {
                  "title": "Advanced Java",
                  "description": "Advanced topics",
                  "isActive": false
                }
                """;
    }
}
