package com.practice.demo.service;

import org.openapitools.model.CreateQuestionDTO;

import java.util.List;

public interface QuestionService {

    Long createQuestion(CreateQuestionDTO dto);

    List<Long> createQuestionsBulk(List<CreateQuestionDTO> dto);
}

