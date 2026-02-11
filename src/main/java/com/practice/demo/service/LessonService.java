package com.practice.demo.service;

import org.openapitools.model.CreateLessonPayload;
import org.openapitools.model.GetLessonPayload;
import org.openapitools.model.UpdateLessonPayload;

public interface LessonService {

    GetLessonPayload getById(Long id);

    GetLessonPayload create(CreateLessonPayload payload);

    GetLessonPayload update(Long id, UpdateLessonPayload payload);

    void delete(Long id);
}
