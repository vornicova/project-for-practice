package com.practice.demo.service;

import com.practice.demo.entity.CourseEntity;
import com.practice.demo.exception.CourseNotFoundException;
import com.practice.demo.mapper.CourseMapper;
import com.practice.demo.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.CreateCoursePayload;
import org.openapitools.model.GetCoursePayload;
import org.openapitools.model.UpdateCoursePayload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    @Transactional
    public GetCoursePayload create(CreateCoursePayload payload) {
        CourseEntity entity = courseMapper.fromCreatePayload(payload);

        CourseEntity saved = courseRepository.save(entity);
        return courseMapper.toGetPayload(saved);

    }

    @Override
    public GetCoursePayload getById(Long id) {
        CourseEntity entity = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        return courseMapper.toGetPayload(entity);
    }

    @Override
    public List<GetCoursePayload> getAll() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toGetPayload)
                .toList();
    }

    @Override
    @Transactional
    public GetCoursePayload update(Long id, UpdateCoursePayload payload) {
        CourseEntity entity = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        CourseEntity updatedEntity = courseMapper.getUpdatedEntityFromPayload(entity, payload);
        return courseMapper.toGetPayload(updatedEntity);
    }
}
