package com.practice.demo.mapper;

import com.practice.demo.entity.LessonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.openapitools.model.CreateLessonPayload;
import org.openapitools.model.GetLessonPayload;
import org.openapitools.model.UpdateLessonPayload;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    @Mapping(target = "courseId", source = "course.id")
    GetLessonPayload toPayload(LessonEntity entity);

    @Mapping(target = "course", ignore = true)
    LessonEntity toEntity(CreateLessonPayload payload);

    @Mapping(target = "course", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateEntity(UpdateLessonPayload payload, @MappingTarget LessonEntity entity);
}
