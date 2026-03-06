package com.practice.demo.mapper;

import com.practice.demo.entity.AnswerEntity;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.CreateQuestionDTO;
import org.openapitools.model.GetQuestionDTO;
import com.practice.demo.entity.QuestionEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionMapper {

    private final AnswerMapper answerMapper;

    public QuestionEntity toEntity(CreateQuestionDTO dto) {
        QuestionEntity question = QuestionEntity.builder()
                .text(dto.getText())
                .build();

        question.setAnswers(
                dto.getAnswers().stream()
                        .map(a -> {
                            AnswerEntity ans = answerMapper.toEntity(a);
                            ans.setQuestion(question);
                            return ans;
                        })
                        .toList()
        );

        return question;
    }

    public GetQuestionDTO toGetQuestionDTO(QuestionEntity entity) {
        GetQuestionDTO dto = new GetQuestionDTO();
        dto.setId(entity.getId());
        dto.setText(entity.getText());
        dto.setAnswers(
                entity.getAnswers().stream()
                        .map(answerMapper::toGetHiddenAnswerDTO)
                        .toList()
        );
        return dto;
    }
}
