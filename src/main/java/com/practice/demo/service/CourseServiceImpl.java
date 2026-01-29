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

    @Override
    @Transactional
    public GetCoursePayload create(CreateCoursePayload payload) {
        CourseEntity entity = CourseMapper.fromCreatePayload(payload);

        CourseEntity saved = courseRepository.save(entity);
        return CourseMapper.toGetPayload(saved);
    }

    @Override
    public GetCoursePayload getById(Long id) {
        CourseEntity entity = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with id=" + id + " was not found"));

        return CourseMapper.toGetPayload(entity);
    }

    @Override
    public List<GetCoursePayload> getAll() {
        return courseRepository.findAll()
                .stream()
                .map(CourseMapper::toGetPayload)
                .toList();
    }

    @Override
    @Transactional
    public GetCoursePayload update(Long id, UpdateCoursePayload payload) {
        CourseEntity entity = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with id=" + id + " was not found"));


        if (payload.getTitle() != null) {
            entity.setTitle(payload.getTitle());
        }
        if (payload.getDescription() != null) {
            entity.setDescription(payload.getDescription());
        }
        if (payload.getIsActive() != null) {
            entity.setActive(payload.getIsActive());
        }

        return CourseMapper.toGetPayload(
                courseRepository.save(entity)
        );
    }
}
