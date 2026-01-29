package com.practice.demo.mapper;

import com.practice.demo.entity.CourseEntity;
import org.openapitools.model.CreateCoursePayload;
import org.openapitools.model.GetCoursePayload;

import java.util.List;


public final class CourseMapper {

    private CourseMapper() {
    }

    public static GetCoursePayload toGetPayload(CourseEntity entity) {
        if (entity == null) {
            return null;
        }

        return new GetCoursePayload()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .isActive(entity.isActive())
                .lessons(List.of());
    }

    public static CourseEntity fromCreatePayload(CreateCoursePayload payload) {
        return CourseEntity.builder()
                .title(payload.getTitle())
                .description(payload.getDescription())
                .isActive(false) // TODO: Confirm whether a course should be inactive upon creation or activated via a dedicated endpoint
                .build();
    }
}
