package com.practice.demo.mapper;

import com.practice.demo.entity.CourseEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.CreateCoursePayload;
import org.openapitools.model.GetCoursePayload;
import org.openapitools.model.UpdateCoursePayload;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CourseMapperTest {

    @InjectMocks
    private CourseMapper courseMapper;

    @Test
    void shouldMapEntityToGetPayload() {
        CourseEntity entity = CourseEntity.builder()
                .id(1L)
                .title("Java")
                .description("Basic")
                .isActive(true)
                .build();
        GetCoursePayload result = courseMapper.toGetPayload(entity);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("Java");
        assertThat(result.getDescription()).isEqualTo("Basic");
        assertThat(result.getIsActive()).isEqualTo(true);
        assertThat(result.getLessons()).isEmpty();

    }

    @Test
    void shouldCreateEntityFromCreatePayloadWithActiveByDefault() {
        CreateCoursePayload payload = new CreateCoursePayload()
                .title("Java")
                .description("Basics");

        CourseEntity entity = courseMapper.fromCreatePayload(payload);

        assertThat(entity.getTitle()).isEqualTo("Java");
        assertThat(entity.getDescription()).isEqualTo("Basics");
        assertThat(entity.isActive()).isTrue();
    }
    @Test
    void shouldUpdateAllFieldsFromPayload() {
        CourseEntity entity = CourseEntity.builder()
                .title("Old")
                .description("Old desc")
                .isActive(false)
                .build();

        UpdateCoursePayload payload = new UpdateCoursePayload()
                .title("New")
                .description("New desc")
                .isActive(true);

        CourseEntity updated = courseMapper.getUpdatedEntityFromPayload(entity, payload);

        assertThat(updated.getTitle()).isEqualTo("New");
        assertThat(updated.getDescription()).isEqualTo("New desc");
        assertThat(updated.isActive()).isTrue();
    }
    @Test
    void shouldIgnoreNullFieldsOnUpdate() {
        CourseEntity entity = CourseEntity.builder()
                .title("Title")
                .description("Desc")
                .isActive(true)
                .build();

        UpdateCoursePayload payload = new UpdateCoursePayload();

        CourseEntity updated = courseMapper.getUpdatedEntityFromPayload(entity, payload);

        assertThat(updated.getTitle()).isEqualTo("Title");
        assertThat(updated.getDescription()).isEqualTo("Desc");
        assertThat(updated.isActive()).isTrue();
    }
}
