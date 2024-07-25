/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:09.07.2024
 * TIME:12:20
 */
package com.telegram_bot.zakovat_bot.service.maper;

import com.telegram_bot.zakovat_bot.entity.Flow;
import com.telegram_bot.zakovat_bot.entity.enumerated.Status;
import com.telegram_bot.zakovat_bot.service.dto.FlowDto;
import org.springframework.stereotype.Component;

@Component
public class FlowMapper {
    public Flow toEntity(FlowDto flowDto) {
        Flow flow = new Flow();
        flow.setAnswerId(flowDto.getAnswerId());
        flow.setQuestionId(flowDto.getQuestionId());
        flow.setUserId(flowDto.getUserId());
        flow.setAnswerId(flowDto.getAnswerId());
        flow.setStatus(Status.valueOf(flowDto.getStatus()));
        return flow;
    }

    public FlowDto toDto(Flow flow) {
        FlowDto flowDto = new FlowDto();
        flowDto.setId(flow.getId());
        flowDto.setAnswerId(flow.getAnswerId());
        flowDto.setQuestionId(flow.getQuestionId());
        flowDto.setUserId(flow.getUserId());
        return flowDto;
    }
}
