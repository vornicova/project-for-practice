package com.practice.demo.service;

import com.practice.demo.entity.CourseEntity;
import com.practice.demo.entity.LessonEntity;
import com.practice.demo.exception.CourseNotFoundException;
import com.practice.demo.exception.LessonNotFoundException;
import com.practice.demo.mapper.LessonMapper;
import com.practice.demo.repository.CourseRepository;
import com.practice.demo.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.openapitools.model.CreateLessonPayload;
import org.openapitools.model.GetLessonPayload;
import org.openapitools.model.UpdateLessonPayload;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository repository;
    private final CourseRepository courseRepository;
    private final LessonMapper mapper;

    @Override
    public GetLessonPayload getById(Long id) {
        LessonEntity entity = repository.findById(id)
                .orElseThrow(() -> new LessonNotFoundException(id));
        return mapper.toPayload(entity);
    }

    @Override
    public GetLessonPayload create(CreateLessonPayload payload) {
        CourseEntity course = courseRepository.findById(payload.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException(payload.getCourseId()));

        LessonEntity entity = mapper.toEntity(payload);
        entity.setCourse(course);
        entity.setIsActive(true);

        return mapper.toPayload(repository.save(entity));
    }

    @Override
    public GetLessonPayload update(Long id, UpdateLessonPayload payload) {
        LessonEntity entity = repository.findById(id)
                .orElseThrow(() -> new LessonNotFoundException(id));

        mapper.updateEntity(payload, entity);

        if (payload.getCourseId() != null) {
            CourseEntity course = courseRepository.findById(payload.getCourseId())
                    .orElseThrow(() -> new CourseNotFoundException(payload.getCourseId()));
            entity.setCourse(course);
        }

        return mapper.toPayload(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        LessonEntity entity = repository.findById(id)
                .orElseThrow();
        entity.setIsActive(false);
        repository.save(entity);
    }
}
