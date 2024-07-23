/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:09.07.2024
 * TIME:11:45
 */
package com.telegram_bot.zakovat_bot.service.maper;

import com.telegram_bot.zakovat_bot.entity.Question;
import com.telegram_bot.zakovat_bot.service.dto.QuestionDto;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {
    public Question toEntity(QuestionDto questionDto) {
        Question question = new Question();
        questionDto.setId(question.getId());
        question.setQuestionText(questionDto.getQuestionText());
        return question;
    }

    public QuestionDto toDto(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(question.getId());
        questionDto.setQuestionText(question.getQuestionText());
        return questionDto;
    }
}
