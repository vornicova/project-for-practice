package com.practice.demo.mapper;

import com.practice.demo.entity.CourseEntity;
import com.practice.demo.testdata.CourseEntityFactory;
import com.practice.demo.testdata.CoursePayloadFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.CreateCoursePayload;
import org.openapitools.model.GetCoursePayload;
import org.openapitools.model.UpdateCoursePayload;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CourseMapperTest {

    @InjectMocks
    private CourseMapper courseMapper;

    @Test
    void shouldMapEntityToGetPayload() {
        CourseEntity entity = CourseEntityFactory.defaultCourse();

        GetCoursePayload result = courseMapper.toGetPayload(entity);

        assertThat(result.getId()).isEqualTo(entity.getId());
        assertThat(result.getTitle()).isEqualTo(entity.getTitle());
        assertThat(result.getDescription()).isEqualTo(entity.getDescription());
        assertThat(result.getIsActive()).isEqualTo(entity.isActive());
        assertThat(result.getLessons()).isEmpty();
    }

    @Test
    void shouldCreateEntityFromCreatePayloadWithActiveByDefault() {
        CreateCoursePayload payload = CoursePayloadFactory.createCoursePayload();

        CourseEntity entity = courseMapper.fromCreatePayload(payload);

        assertThat(entity.getTitle()).isEqualTo(payload.getTitle());
        assertThat(entity.getDescription()).isEqualTo(payload.getDescription());
        assertThat(entity.isActive()).isTrue();
    }

    @Test
    void shouldUpdateAllFieldsFromPayload() {
        CourseEntity entity = CourseEntityFactory.defaultCourse();
        UpdateCoursePayload payload = CoursePayloadFactory.updateCoursePayload();

        CourseEntity updated = courseMapper.getUpdatedEntityFromPayload(entity, payload);

        assertThat(updated.getTitle()).isEqualTo(payload.getTitle());
        assertThat(updated.getDescription()).isEqualTo(payload.getDescription());
        assertThat(updated.isActive()).isEqualTo(payload.getIsActive());
    }

    @Test
    void shouldIgnoreNullFieldsOnUpdate() {
        CourseEntity entity = CourseEntityFactory.defaultCourse();
        UpdateCoursePayload payload = new UpdateCoursePayload();

        CourseEntity updated = courseMapper.getUpdatedEntityFromPayload(entity, payload);

        assertThat(updated.getTitle()).isEqualTo(entity.getTitle());
        assertThat(updated.getDescription()).isEqualTo(entity.getDescription());
        assertThat(updated.isActive()).isEqualTo(entity.isActive());
    }
}
