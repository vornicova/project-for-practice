package com.practice.demo.mapper;

import org.openapitools.model.CreateAnswerDTO;
import org.openapitools.model.GetHiddenAnswerDTO;
import com.practice.demo.entity.AnswerEntity;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {

    public AnswerEntity toEntity(CreateAnswerDTO dto) {
        return AnswerEntity.builder()
                .text(dto.getText())
                .isCorrect(dto.getIsCorrect())
                .build();
    }

    public GetHiddenAnswerDTO toGetHiddenAnswerDTO(AnswerEntity entity) {
        GetHiddenAnswerDTO dto = new GetHiddenAnswerDTO();
        dto.setId(entity.getId());
        dto.setText(entity.getText());
        return dto;
    }
}
