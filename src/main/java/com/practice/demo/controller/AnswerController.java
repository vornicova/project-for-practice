package com.practice.demo.controller;

import com.practice.demo.entity.AnswerEntity;
import com.practice.demo.mapper.AnswerMapper;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.CreateAnswerDTO;
import org.openapitools.model.GetHiddenAnswerDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerMapper answerMapper;

    @PostMapping("/answers")
    public GetHiddenAnswerDTO createAnswer(@RequestBody CreateAnswerDTO dto) {
        AnswerEntity entity = answerMapper.toEntity(dto);
        return answerMapper.toGetHiddenAnswerDTO(entity);
    }
}