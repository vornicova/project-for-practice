package com.practice.demo.mapper;

import org.openapitools.model.CreateQuestionDTO;
import org.openapitools.model.GetQuestionDTO;
import com.practice.demo.entity.QuestionEntity;

import java.util.List;

public class QuestionMapper {

    public static QuestionEntity toEntity(CreateQuestionDTO dto) {
        QuestionEntity question = QuestionEntity.builder()
                .text(dto.getText())
                .build();

        List answers = dto.getAnswers().stream()
                .map(AnswerMapper::toEntity)
                .peek(a -> a.setQuestion(question)) // привязываем к вопросу
                .toList();

        question.setAnswers(answers);
        return question;
    }

    public static GetQuestionDTO toGetQuestionDTO(QuestionEntity entity) {
        GetQuestionDTO dto = new GetQuestionDTO();
        dto.setId(entity.getId());
        dto.setText(entity.getText());
        dto.setAnswers(
                entity.getAnswers().stream()
                        .map(AnswerMapper::toGetHiddenAnswerDTO)
                        .toList()
        );
        return dto;
    }
}