package com.practice.demo.mapper;

import com.practice.demo.entity.LessonEntity;
import org.openapitools.model.CreateLessonPayload;
import org.openapitools.model.GetLessonPayload;
import org.openapitools.model.UpdateLessonPayload;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {

    public GetLessonPayload toPayload(LessonEntity entity) {
        return new GetLessonPayload()
                .id(entity.getId())
                .name(entity.getName())
                .isActive(entity.getIsActive())
                .courseId(entity.getCourse() != null ? entity.getCourse().getId() : null);
    }

    public LessonEntity toEntity(CreateLessonPayload payload) {
        return LessonEntity.builder()
                .name(payload.getName())
                .isActive(true)
                .build();
    }

    public void updateEntity(UpdateLessonPayload payload, LessonEntity entity) {
        if (payload.getName() != null) {
            entity.setName(payload.getName());
        }
        if (payload.getIsActive() != null) {
            entity.setIsActive(payload.getIsActive());
        }
    }
}
