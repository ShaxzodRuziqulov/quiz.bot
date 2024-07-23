/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:09.07.2024
 * TIME:11:19
 */
package com.telegram_bot.zakovat_bot.service.dto;

import lombok.Data;

@Data
public class AnswerDto {
    private Long id;
    private String answerText;
    private Integer isCorrect;
    private Long questionId;
    private String answerOption;
}
