package com.practice.demo.service;

import org.openapitools.model.CreateTestDTO;
import org.openapitools.model.GetTestDTO;

import java.util.List;

public interface TestService {

    Long createTest(CreateTestDTO dto);

    GetTestDTO getTestById(Long id);

    void addQuestionsToTest(Long testId, List<Long> questionIds);
}