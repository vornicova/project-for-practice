package com.practice.demo.mapper;

import lombok.RequiredArgsConstructor;
import org.openapitools.model.CreateTestDTO;
import org.openapitools.model.GetTestDTO;
import com.practice.demo.entity.TestEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestMapper {
    private final QuestionMapper questionMapper;

    public TestEntity toEntity(CreateTestDTO dto) {
        return TestEntity.builder()
                .title(dto.getName())
                .description(dto.getDescription())
                .isActive(true)
                .build();
    }

    public GetTestDTO toGetTestDTO(TestEntity entity) {
        GetTestDTO dto = new GetTestDTO();
        dto.setName(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setQuestions(
                entity.getQuestions().stream()
                        .map(questionMapper::toGetQuestionDTO)
                        .toList()
        );
        return dto;
    }
}