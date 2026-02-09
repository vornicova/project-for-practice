package com.practice.demo.service;

import com.practice.demo.entity.CourseEntity;
import com.practice.demo.exception.CourseNotFoundException;
import com.practice.demo.mapper.CourseMapper;
import com.practice.demo.repository.CourseRepository;
import com.practice.demo.testdata.CourseEntityFactory;
import com.practice.demo.testdata.CoursePayloadFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.CreateCoursePayload;
import org.openapitools.model.GetCoursePayload;
import org.openapitools.model.UpdateCoursePayload;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void shouldCreateCourse() {
        CreateCoursePayload payload = CoursePayloadFactory.createCoursePayload();
        CourseEntity entity = CourseEntityFactory.defaultCourse();
        CourseEntity savedEntity = CourseEntityFactory.defaultCourse();
        GetCoursePayload response = CoursePayloadFactory.getCoursePayload();

        when(courseMapper.fromCreatePayload(payload)).thenReturn(entity);
        when(courseRepository.save(entity)).thenReturn(savedEntity);
        when(courseMapper.toGetPayload(savedEntity)).thenReturn(response);

        GetCoursePayload result = courseService.create(payload);

        assertThat(result.getId()).isEqualTo(response.getId());
        assertThat(result.getTitle()).isEqualTo(response.getTitle());
        assertThat(result.getIsActive()).isTrue();

        verify(courseRepository).save(entity);
    }

    @Test
    void shouldReturnCourseById() {
        long id = 1L;
        CourseEntity entity = CourseEntityFactory.defaultCourse();
        GetCoursePayload payload = CoursePayloadFactory.getCoursePayload();

        when(courseRepository.findById(id)).thenReturn(Optional.of(entity));
        when(courseMapper.toGetPayload(entity)).thenReturn(payload);

        GetCoursePayload result = courseService.getById(id);

        assertThat(result.getId()).isEqualTo(payload.getId());
        assertThat(result.getTitle()).isEqualTo(payload.getTitle());
    }

    @Test
    void shouldThrowExceptionWhenCourseNotFound() {
        long id = 99L;
        when(courseRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> courseService.getById(id))
                .isInstanceOf(CourseNotFoundException.class)
                .hasMessageContaining(String.valueOf(id));
    }

    @Test
    void shouldReturnAllCourses() {
        CourseEntity first = CourseEntityFactory.defaultCourse();
        CourseEntity second = CourseEntity.builder()
                .id(2L)
                .title("C#")
                .description("Basics")
                .isActive(true)
                .build();

        GetCoursePayload firstPayload = CoursePayloadFactory.getCoursePayload();
        GetCoursePayload secondPayload = new GetCoursePayload()
                .id(2L)
                .title("C#")
                .description("Basics")
                .isActive(true);

        when(courseRepository.findAll()).thenReturn(List.of(first, second));
        when(courseMapper.toGetPayload(first)).thenReturn(firstPayload);
        when(courseMapper.toGetPayload(second)).thenReturn(secondPayload);

        List<GetCoursePayload> result = courseService.getAll();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getTitle()).isEqualTo("Java");
        assertThat(result.get(1).getTitle()).isEqualTo("C#");
    }

    @Test
    void shouldUpdateCourse() {
        long id = 1L;

        UpdateCoursePayload payload = CoursePayloadFactory.updateCoursePayload();
        CourseEntity existing = CourseEntityFactory.defaultCourse();
        CourseEntity updated = CourseEntityFactory.updatedCourse();

        GetCoursePayload response = CoursePayloadFactory.updatedGetCoursePayload();

        when(courseRepository.findById(id)).thenReturn(Optional.of(existing));
        when(courseMapper.getUpdatedEntityFromPayload(existing, payload)).thenReturn(updated);
        when(courseMapper.toGetPayload(updated)).thenReturn(response);

        GetCoursePayload result = courseService.update(id, payload);

        assertThat(result.getTitle()).isEqualTo("Advanced Java");
        assertThat(result.getIsActive()).isFalse();
    }

    @Test
    void shouldThrowExceptionWhenUpdatingMissingCourse() {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> courseService.update(1L, CoursePayloadFactory.updateCoursePayload()))
                .isInstanceOf(CourseNotFoundException.class);
    }
}
