package com.practice.demo.controller;

import com.practice.demo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.CoursesApi;
import org.openapitools.model.CreateCoursePayload;
import org.openapitools.model.GetCoursePayload;
import org.openapitools.model.UpdateCoursePayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CoursesController implements CoursesApi {

    private final CourseService courseService;

    @Override
    public ResponseEntity<GetCoursePayload> createCourse(
            CreateCoursePayload createCoursePayload
    ) {
        return ResponseEntity
                .status(201)
                .body(courseService.create(createCoursePayload));
    }

    @Override
    public ResponseEntity<GetCoursePayload> getCourseById(Long id) {
        return ResponseEntity.ok(courseService.getById(id));
    }

    @Override
    public ResponseEntity<List<GetCoursePayload>> getCourses() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @Override
    public ResponseEntity<GetCoursePayload> updateCourse(
            Long id,
            UpdateCoursePayload updateCoursePayload
    ) {
        return ResponseEntity.ok(courseService.update(id, updateCoursePayload));
    }
}
