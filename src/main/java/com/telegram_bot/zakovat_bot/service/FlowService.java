/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:09.07.2024
 * TIME:12:17
 */
package com.telegram_bot.zakovat_bot.service;

import com.telegram_bot.zakovat_bot.entity.Flow;
import com.telegram_bot.zakovat_bot.entity.enumerated.Status;
import com.telegram_bot.zakovat_bot.repository.FlowRepository;
import com.telegram_bot.zakovat_bot.service.dto.FlowDto;
import com.telegram_bot.zakovat_bot.service.maper.FlowMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowService {
    public final FlowRepository flowRepository;
    public final FlowMapper flowMapper;
    public final QuestionService questionService;

    public FlowService(FlowRepository flowRepository, FlowMapper flowMapper, QuestionService questionService) {
        this.flowRepository = flowRepository;
        this.flowMapper = flowMapper;
        this.questionService = questionService;
    }

    public FlowDto create(FlowDto flowDto) {
        Flow flow = flowMapper.toEntity(flowDto);
        flow = flowRepository.save(flow);
        return flowMapper.toDto(flow);
    }

    public FlowDto update(FlowDto flowDto) {
        Flow flow = flowMapper.toEntity(flowDto);
        flow = flowRepository.save(flow);
        return flowMapper.toDto(flow);
    }

    public List<Flow> findAll() {
        return flowRepository.findAll();
    }

    public Flow findById(Long id) {
        return flowRepository.findById(id).orElseGet(Flow::new);
    }

    public void delete(Long id) {
        Flow flow = flowRepository.getReferenceById(id);
        flowRepository.delete(flow);
    }

    public void saveUserAnswer(Long userId, Long questionId, Long answerId) {
        Flow flow = new Flow();
        flow.setUserId(userId);
        flow.setQuestionId(questionId);
        flow.setAnswerId(answerId);
        flow.setStatus(Status.NEW);
        flowRepository.save(flow);
    }

    public long countCorrectAnswers(Long userId) {
        return flowRepository.findByUserIdAndStatus(userId, Status.NEW).stream()
                .filter(flow -> questionService.checkAnswer(flow.getQuestionId(), flow.getAnswerId())
                        .orElse(0) == 1)
                .count();
    }

    public long countIncorrectAnswers(Long userId) {
        return flowRepository.findByUserIdAndStatus(userId, Status.NEW).stream()
                .filter(flow -> questionService.checkAnswer(flow.getQuestionId(), flow.getAnswerId())
                        .orElse(0) == 0)
                .count();
    }

    @Transactional
    public void updateFlowStatusToOld(Long userId) {
        flowRepository.updateFlowStatus(Status.NEW, userId, Status.OLD);
    }
}
