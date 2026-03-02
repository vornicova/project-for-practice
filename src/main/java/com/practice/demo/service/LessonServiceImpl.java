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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LessonServiceImpl implements LessonService {

    private final LessonRepository repository;
    private final CourseRepository courseRepository;
    private final LessonMapper lessonMapper;

    @Override
    public GetLessonPayload getById(Long id) {
        LessonEntity entity = repository.findById(id)
                .orElseThrow(() -> new LessonNotFoundException(id));
        return lessonMapper.toPayload(entity);
    }

    @Override
    @Transactional
    public GetLessonPayload create(CreateLessonPayload payload) {
        CourseEntity course = courseRepository.findById(payload.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException(payload.getCourseId()));

        LessonEntity entity = lessonMapper.toEntity(payload);
        entity.setCourse(course);
        entity.setIsActive(true);

        return lessonMapper.toPayload(repository.save(entity));
    }

    @Override
    @Transactional
    public GetLessonPayload update(Long id, UpdateLessonPayload payload) {
        LessonEntity entity = repository.findById(id)
                .orElseThrow(() -> new LessonNotFoundException(id));

        lessonMapper.updateEntity(payload, entity);

        if (payload.getCourseId() != null &&
                !payload.getCourseId().equals(entity.getCourse().getId())) {

            CourseEntity course = courseRepository.findById(payload.getCourseId())
                    .orElseThrow(() -> new CourseNotFoundException(payload.getCourseId()));

            entity.setCourse(course);
        }

        return lessonMapper.toPayload(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        LessonEntity entity = repository.findById(id)
                .orElseThrow(() -> new LessonNotFoundException(id));
        entity.setIsActive(false);
    }
}
