package com.practice.demo.controller;

import com.practice.demo.service.CourseService;
import com.practice.demo.testdata.CourseJsonFactory;
import com.practice.demo.testdata.CoursePayloadFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CoursesController.class)
class CoursesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CourseService courseService;

    @Test
    void shouldCreateCourse() throws Exception {
        when(courseService.create(any()))
                .thenReturn(CoursePayloadFactory.getCoursePayload());

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CourseJsonFactory.createCourseJson()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Java"))
                .andExpect(jsonPath("$.isActive").value(true));
    }

    @Test
    void shouldGetCourseById() throws Exception {
        when(courseService.getById(1L))
                .thenReturn(CoursePayloadFactory.getCoursePayload());

        mockMvc.perform(get("/courses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Java"));
    }

    @Test
    void shouldGetAllCourses() throws Exception {
        when(courseService.getAll())
                .thenReturn(List.of(CoursePayloadFactory.getCoursePayload()));

        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Java"));
    }

    @Test
    void shouldUpdateCourse() throws Exception {
        when(courseService.update(eq(1L), any()))
                .thenReturn(CoursePayloadFactory.updatedGetCoursePayload());

        mockMvc.perform(put("/courses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CourseJsonFactory.updateCourseJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Advanced Java"));
    }
}
