package com.practice.demo.controller;

import com.practice.demo.service.TestService;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.CreateTestDTO;
import org.openapitools.model.GetTestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tests")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @PostMapping
    public Long createTest(@RequestBody CreateTestDTO dto) {
        return testService.createTest(dto);
    }

    @GetMapping("/{id}")
    public GetTestDTO getTest(@PathVariable Long id) {
        return testService.getTestById(id);
    }

    @PostMapping("/{testId}/addQuestions")
    public void addQuestions(@PathVariable Long testId, @RequestBody List<Long> questionIds) {
        testService.addQuestionsToTest(testId, questionIds);
    }
}