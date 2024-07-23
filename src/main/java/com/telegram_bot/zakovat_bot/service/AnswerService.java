/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:07.07.2024
 * TIME:19:57
 */
package com.telegram_bot.zakovat_bot.service;

import com.telegram_bot.zakovat_bot.entity.Answer;
import com.telegram_bot.zakovat_bot.repository.AnswerRepository;
import com.telegram_bot.zakovat_bot.service.dto.AnswerDto;
import com.telegram_bot.zakovat_bot.service.maper.AnswerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private final AnswerMapper answerMapper;
    private final AnswerRepository answerRepository;


    public AnswerService(AnswerMapper answerMapper, AnswerRepository answerRepository) {
        this.answerMapper = answerMapper;
        this.answerRepository = answerRepository;
    }

    public AnswerDto create(AnswerDto answerDto) {

        Answer answer = answerMapper.toEntity(answerDto);

        answer = answerRepository.save(answer);

        return answerMapper.toDto(answer);
    }

    public AnswerDto update(AnswerDto answerDto) {
        Answer answer = answerMapper.toEntity(answerDto);

        answer = answerRepository.save(answer);

        return answerMapper.toDto(answer);
    }

    public List<Answer> findAllAnswers() {
        return answerRepository.findAll();
    }

    public Answer finByIdAnswers(Long id) {
        return answerRepository.findById(id).orElseGet(Answer::new);
    }

    public void delete(Long id) {
        Answer answer = answerRepository.getReferenceById(id);
        answerRepository.delete(answer);
    }

    public List<Answer> getAnswersByQuestionId(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }
}
