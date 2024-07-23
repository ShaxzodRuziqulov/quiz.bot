/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:09.07.2024
 * TIME:11:22
 */
package com.telegram_bot.zakovat_bot.service.maper;

import com.telegram_bot.zakovat_bot.entity.Answer;
import com.telegram_bot.zakovat_bot.entity.enumerated.AnswerOption;
import com.telegram_bot.zakovat_bot.repository.QuestionRepository;
import com.telegram_bot.zakovat_bot.service.dto.AnswerDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {
    private final QuestionRepository questionRepository;

    public AnswerMapper(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Answer toEntity(AnswerDto answerDto) {
        Answer answer = new Answer();
        answer.setId(answerDto.getId());
        answer.setAnswerText(answerDto.getAnswerText());
        answer.setAnswerOption(AnswerOption.valueOf(answerDto.getAnswerOption()));
        answer.setQuestion(questionRepository.findById(answerDto.getQuestionId()).orElseThrow(() -> new EntityNotFoundException("not found question")));
        answer.setIsCorrect(answerDto.getIsCorrect());
        return answer;
    }

    public AnswerDto toDto(Answer answer) {
        if (answer == null) {
            return null;
        }
        AnswerDto answerDto = new AnswerDto();
        answerDto.setId(answer.getId());
        answerDto.setAnswerText(answer.getAnswerText());
        answerDto.setAnswerOption(String.valueOf(answer.getAnswerOption()));
        answerDto.setIsCorrect(answer.getIsCorrect());
        answerDto.setQuestionId(answer.getQuestion().getId());
        return answerDto;
    }
}
