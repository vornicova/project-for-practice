package com.practice.demo.service;

import com.practice.demo.entity.CourseEntity;
import com.practice.demo.exception.CourseNotFoundException;
import com.practice.demo.mapper.CourseMapper;
import com.practice.demo.repository.CourseRepository;
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
        CreateCoursePayload payload = new CreateCoursePayload()
                .title("Java")
                .description("Basics");

        CourseEntity entity = CourseEntity.builder()
                .title("Java")
                .description("Basics")
                .isActive(true)
                .build();

        CourseEntity savedEntity = CourseEntity.builder()
                .id(1L)
                .title("Java")
                .description("Basics")
                .isActive(true)
                .build();

        GetCoursePayload response = new GetCoursePayload()
                .id(1L)
                .title("Java")
                .description("Basics")
                .isActive(true);

        when(courseMapper.fromCreatePayload(payload)).thenReturn(entity);
        when(courseRepository.save(entity)).thenReturn(savedEntity);
        when(courseMapper.toGetPayload(savedEntity)).thenReturn(response);

        GetCoursePayload result = courseService.create(payload);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("Java");
        assertThat(result.getIsActive()).isTrue();

        verify(courseRepository).save(entity);
    }

    @Test
    void shouldReturnCourseById() {
        Long id = 1L;

        CourseEntity entity = CourseEntity.builder()
                .id(id)
                .title("Java")
                .build();

        GetCoursePayload payload = new GetCoursePayload()
                .id(id)
                .title("Java");

        when(courseRepository.findById(id)).thenReturn(Optional.of(entity));
        when(courseMapper.toGetPayload(entity)).thenReturn(payload);

        GetCoursePayload result = courseService.getById(id);

        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getTitle()).isEqualTo("Java");
    }

    @Test
    void shouldThrowExceptionWhenCourseNotFound() {
        Long id = 99L;
        when(courseRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> courseService.getById(id))
                .isInstanceOf(CourseNotFoundException.class)
                .hasMessageContaining(String.valueOf(id));
    }

    @Test
    void shouldReturnAllCourses() {
        CourseEntity firstCourse = CourseEntity.builder()
                .id(1L)
                .title("Java")
                .build();
        CourseEntity secondCourse = CourseEntity.builder()
                .id(2L)
                .title("C#")
                .build();
        List<CourseEntity> entities = List.of(firstCourse, secondCourse);

        GetCoursePayload firstPayload = new GetCoursePayload()
                .id(1L)
                .title("Java");
        GetCoursePayload secondPayload = new GetCoursePayload()
                .id(2L)
                .title("C#");

        when(courseRepository.findAll()).thenReturn(entities);
        when(courseMapper.toGetPayload(firstCourse)).thenReturn(firstPayload);
        when(courseMapper.toGetPayload(secondCourse)).thenReturn(secondPayload);

        List<GetCoursePayload> result = courseService.getAll();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getTitle()).isEqualTo("Java");
        assertThat(result.get(1).getTitle()).isEqualTo("C#");
    }

    @Test
    void shouldUpdateCourse() {
        Long id = 1L;

        UpdateCoursePayload payload = new UpdateCoursePayload()
                .title("Advanced Java");

        CourseEntity entity = CourseEntity.builder()
                .id(id)
                .title("Java")
                .build();

        CourseEntity updatedEntity = CourseEntity.builder()
                .id(id)
                .title("Advanced Java")
                .build();

        GetCoursePayload response = new GetCoursePayload()
                .id(id)
                .title("Advanced Java");

        when(courseRepository.findById(id)).thenReturn(Optional.of(entity));
        when(courseMapper.getUpdatedEntityFromPayload(entity, payload)).thenReturn(updatedEntity);
        when(courseMapper.toGetPayload(updatedEntity)).thenReturn(response);

        GetCoursePayload result = courseService.update(id, payload);
        assertThat(result.getTitle()).isEqualTo("Advanced Java");
    }

    @Test
    void shouldThrowExceptionWhenUpdatingMissingCourse() {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> courseService.update(1L, new UpdateCoursePayload()))
                .isInstanceOf(CourseNotFoundException.class);
    }
}

