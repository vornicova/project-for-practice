package com.practice.demo.service;

import com.practice.demo.entity.QuestionEntity;
import com.practice.demo.mapper.QuestionMapper;
import com.practice.demo.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.CreateQuestionDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Override
    @Transactional
    public Long createQuestion(CreateQuestionDTO dto) {
        QuestionEntity question = questionMapper.toEntity(dto);
        questionRepository.save(question);
        return question.getId();
    }

    @Override
    @Transactional
    public List<Long> createQuestionsBulk(List<CreateQuestionDTO> dtos) {
        List<QuestionEntity> questions = dtos.stream()
                .map(questionMapper::toEntity)
                .toList();
        questionRepository.saveAll(questions);
        return questions.stream().map(QuestionEntity::getId).toList();
    }
}
