package com.practice.demo.mapper;

import com.practice.demo.entity.CourseEntity;
import org.openapitools.model.CreateCoursePayload;
import org.openapitools.model.GetCoursePayload;
import org.openapitools.model.GetLessonReducedPayload;
import org.openapitools.model.UpdateCoursePayload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class CourseMapper {

    public GetCoursePayload toGetPayload(CourseEntity entity) {

        List<GetLessonReducedPayload> lessons = entity.getLessons().stream()
                .filter(l -> Boolean.TRUE.equals(l.getIsActive()))
                .map(lesson -> new GetLessonReducedPayload()
                        .id(lesson.getId())
                        .name(lesson.getName())
                )
                .toList();

        return new GetCoursePayload()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .isActive(entity.isActive())
                .lessons(lessons);
    }

    public CourseEntity fromCreatePayload(CreateCoursePayload payload) {
        return CourseEntity.builder()
                .title(payload.getTitle())
                .description(payload.getDescription())
                .isActive(true)
                .build();
    }

    public CourseEntity getUpdatedEntityFromPayload(
            CourseEntity entity,
            UpdateCoursePayload payload
    ) {
        if (payload.getTitle() != null) {
            entity.setTitle(payload.getTitle());
        }
        if (payload.getDescription() != null) {
            entity.setDescription(payload.getDescription());
        }
        if (payload.getIsActive() != null) {
            entity.setActive(payload.getIsActive());
        }
        return entity;
    }
}
