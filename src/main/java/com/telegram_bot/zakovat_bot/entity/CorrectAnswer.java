/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:27.07.2024
 * TIME:16:33
 */
package com.telegram_bot.zakovat_bot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "correctAnswer")
public class CorrectAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long questionId;
    private String text;
}
