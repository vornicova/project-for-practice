package com.practice.demo.service;

import org.openapitools.model.CreateCoursePayload;
import org.openapitools.model.GetCoursePayload;
import org.openapitools.model.UpdateCoursePayload;

import java.util.List;

public interface CourseService {
    //TODO: Assess whether returning GetCoursePayload is necessary for this operation
    GetCoursePayload create(CreateCoursePayload payload);

    GetCoursePayload getById(Long id);

    List<GetCoursePayload> getAll();
    //TODO: Assess whether returning GetCoursePayload is necessary for this operation
    GetCoursePayload update(Long id, UpdateCoursePayload payload);
}
