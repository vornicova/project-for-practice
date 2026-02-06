package com.practice.demo.testdata;

import org.openapitools.model.CreateCoursePayload;
import org.openapitools.model.GetCoursePayload;
import org.openapitools.model.UpdateCoursePayload;

public final class CoursePayloadFactory {

    private CoursePayloadFactory() {
    }

    public static CreateCoursePayload createCoursePayload() {
        return new CreateCoursePayload()
                .title("Java")
                .description("Basics");
    }

    public static UpdateCoursePayload updateCoursePayload() {
        return new UpdateCoursePayload()
                .title("Advanced Java")
                .description("Advanced topics")
                .isActive(false);
    }

    public static GetCoursePayload getCoursePayload() {
        return new GetCoursePayload()
                .id(1L)
                .title("Java")
                .description("Basics")
                .isActive(true);
    }

    public static GetCoursePayload updatedGetCoursePayload() {
        return new GetCoursePayload()
                .id(1L)
                .title("Advanced Java")
                .description("Advanced topics")
                .isActive(false);
    }
}
