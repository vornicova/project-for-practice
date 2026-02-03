package com.practice.demo.controller;

import com.practice.demo.service.CourseService;
import org.junit.jupiter.api.Test;
import org.openapitools.model.CreateCoursePayload;
import org.openapitools.model.GetCoursePayload;
import org.openapitools.model.UpdateCoursePayload;
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
public class CoursesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CourseService courseService;

    @Test
    void shouldCreateCourse() throws Exception {
        GetCoursePayload response = new GetCoursePayload()
                .id(1L)
                .title("Java")
                .description("Basics")
                .isActive(true);

        when(courseService.create(any(CreateCoursePayload.class)))
                .thenReturn(response);

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                      "title": "Java",
                                      "description": "Basics"
                                    }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Java"))
                .andExpect(jsonPath("$.isActive").value(true));
    }

    @Test
    void shouldGetCourseById() throws Exception {
        GetCoursePayload payload = new GetCoursePayload()
                .id(1L)
                .title("Java");

        when(courseService.getById(1L)).thenReturn(payload);

        mockMvc.perform(get("/courses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Java"));
    }

    @Test
    void shouldGetAllCourses() throws Exception {
        GetCoursePayload payload = new GetCoursePayload()
                .id(1L)
                .title("Java");

        when(courseService.getAll()).thenReturn(List.of(payload));

        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Java"));
    }

    @Test
    void shouldUpdateCourse() throws Exception {
        GetCoursePayload payload = new GetCoursePayload()
                .id(1L)
                .title("Advanced Java");

        when(courseService.update(eq(1L), any(UpdateCoursePayload.class)))
                .thenReturn(payload);

        mockMvc.perform(put("/courses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                      "title": "Advanced Java"
                                    }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Advanced Java"));
    }
}
