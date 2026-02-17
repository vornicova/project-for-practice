package com.practice.demo.controller;

import com.practice.demo.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.LessonsApi;
import org.openapitools.model.CreateLessonPayload;
import org.openapitools.model.GetLessonPayload;
import org.openapitools.model.UpdateLessonPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LessonsController implements LessonsApi {

    private final LessonService lessonService;

    @Override
    public ResponseEntity<GetLessonPayload> createLesson(
            CreateLessonPayload createLessonPayload
    ) {
        return ResponseEntity
                .status(201)
                .body(lessonService.create(createLessonPayload));
    }

    @Override
    public ResponseEntity<GetLessonPayload> getLessonById(Long id) {
        return ResponseEntity.ok(lessonService.getById(id));
    }

    @Override
    public ResponseEntity<GetLessonPayload> updateLesson(
            Long id,
            UpdateLessonPayload updateLessonPayload
    ) {
        return ResponseEntity.ok(lessonService.update(id, updateLessonPayload));
    }

    @Override
    public ResponseEntity<Void> deleteLesson(Long id) {
        lessonService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
