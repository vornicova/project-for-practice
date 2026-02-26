package com.practice.demo.mapper;

import org.openapitools.model.CreateTestDTO;
import org.openapitools.model.GetTestDTO;
import com.practice.demo.entity.TestEntity;

public class TestMapper {

    public static TestEntity toEntity(CreateTestDTO dto) {
        return TestEntity.builder()
                .title(dto.getName())
                .description(dto.getDescription())
                .isActive(true)
                .build();
    }

    public static GetTestDTO toGetTestDTO(TestEntity entity) {
        GetTestDTO dto = new GetTestDTO();
        dto.setName(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setQuestions(
                entity.getQuestions().stream()
                        .map(QuestionMapper::toGetQuestionDTO)
                        .toList()
        );
        return dto;
    }
}