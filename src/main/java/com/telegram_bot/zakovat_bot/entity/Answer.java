/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:04.07.2024
 * TIME:0:35
 */
package com.telegram_bot.zakovat_bot.entity;

import com.telegram_bot.zakovat_bot.entity.enumerated.AnswerOption;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String answerText;
    private Integer isCorrect;
    @Enumerated(EnumType.STRING)
    private AnswerOption answerOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

}
