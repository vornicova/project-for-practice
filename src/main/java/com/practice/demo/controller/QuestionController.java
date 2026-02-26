package com.practice.demo.controller;

import com.practice.demo.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.CreateQuestionDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public Long createQuestion(@RequestBody CreateQuestionDTO dto) {
        return questionService.createQuestion(dto);
    }

    @PostMapping("/bulk")
    public List<Long> createQuestionsBulk(@RequestBody List<CreateQuestionDTO> dtos) {
        return questionService.createQuestionsBulk(dtos);
    }
}