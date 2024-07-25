/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:09.07.2024
 * TIME:12:21
 */
package com.telegram_bot.zakovat_bot.service.dto;

import lombok.Data;

@Data
public class FlowDto {
    private Long id;

    private Long answerId;

    private Long questionId;

    private Long userId;
    private String status;
}
