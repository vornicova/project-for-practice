package com.practice.demo.service;

import com.practice.demo.entity.QuestionEntity;
import com.practice.demo.entity.TestEntity;
import com.practice.demo.mapper.TestMapper;
import com.practice.demo.repository.QuestionRepository;
import com.practice.demo.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.CreateTestDTO;
import org.openapitools.model.GetTestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
    private final TestMapper testMapper;

    @Override
    @Transactional
    public Long createTest(CreateTestDTO dto) {
        TestEntity test = testMapper.toEntity(dto);
        testRepository.save(test);
        return test.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public GetTestDTO getTestById(Long id) {
        TestEntity test = testRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test not found"));
        return testMapper.toGetTestDTO(test);
    }

    @Override
    @Transactional
    public void addQuestionsToTest(Long testId, List<Long> questionIds) {
        TestEntity test = testRepository.findById(testId)
                .orElseThrow(() -> new RuntimeException("Test not found"));

        List<QuestionEntity> questions = questionRepository.findAllById(questionIds);
        test.getQuestions().addAll(questions);

        testRepository.save(test);
    }
}