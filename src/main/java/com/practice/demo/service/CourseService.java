package com.practice.demo.service;

import org.openapitools.model.CreateCoursePayload;
import org.openapitools.model.GetCoursePayload;
import org.openapitools.model.UpdateCoursePayload;

import java.util.List;

public interface CourseService {
    GetCoursePayload create(CreateCoursePayload payload);

    GetCoursePayload getById(Long id);

    List<GetCoursePayload> getAll();
    GetCoursePayload update(Long id, UpdateCoursePayload payload);
}
