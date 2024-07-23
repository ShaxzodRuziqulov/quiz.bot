/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:04.07.2024
 * TIME:0:52
 */
package com.telegram_bot.zakovat_bot.service;

import com.telegram_bot.zakovat_bot.entity.Answer;
import com.telegram_bot.zakovat_bot.entity.Question;
import com.telegram_bot.zakovat_bot.repository.AnswerRepository;
import com.telegram_bot.zakovat_bot.repository.QuestionRepository;
import com.telegram_bot.zakovat_bot.service.dto.QuestionDto;
import com.telegram_bot.zakovat_bot.service.maper.QuestionMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final AnswerRepository answerRepository;

    public QuestionService(QuestionRepository questionRepository, QuestionMapper questionMapper, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
        this.answerRepository = answerRepository;
    }

    public QuestionDto create(QuestionDto questionDto) {
        Question question = questionMapper.toEntity(questionDto);
        question = questionRepository.save(question);
        return questionMapper.toDto(question);
    }

    public QuestionDto update(QuestionDto questionDto) {
        Question question = questionMapper.toEntity(questionDto);
        question = questionRepository.save(question);
        return questionMapper.toDto(question);
    }

    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }

    public Question findByIdQuestion(Long id) {
        return questionRepository.findById(id).orElseGet(Question::new);
    }

    public void delete(Long id) {
        Question question = questionRepository.getReferenceById(id);
        questionRepository.delete(question);
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }


    public Question getFirstQuestion() {
        return questionRepository.findAll().stream().findFirst().orElse(null);
    }

    public Question getNextQuestion(Long currentQuestionId) {
        List<Question> questions = questionRepository.findAll();
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getId().equals(currentQuestionId) && i + 1 < questions.size()) {
                return questions.get(i + 1);
            }
        }
        return null;
    }

    public Optional<Integer> checkAnswer(Long questionId, Long answerId) {
        Optional<Answer> answerOpt = answerRepository.findById(answerId);
        return answerOpt.map(Answer::getIsCorrect);
    }

    public String getRandomQuestion() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            return "Savollar mavjud emas.";
        }
        Random random = new Random();
        return questions.get(random.nextInt(questions.size())).getQuestionText();
    }
}