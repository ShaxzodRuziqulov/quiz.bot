/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:27.07.2024
 * TIME:18:58
 */
package com.telegram_bot.zakovat_bot.service;

import com.telegram_bot.zakovat_bot.entity.CorrectAnswer;
import com.telegram_bot.zakovat_bot.repository.CorrectAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorrectAnswerService {
    private final CorrectAnswerRepository correctAnswerRepository;

    public CorrectAnswerService(CorrectAnswerRepository correctAnswerRepository) {
        this.correctAnswerRepository = correctAnswerRepository;
    }
    public List<CorrectAnswer> findAll(){
        return correctAnswerRepository.findAll();
    }
}
