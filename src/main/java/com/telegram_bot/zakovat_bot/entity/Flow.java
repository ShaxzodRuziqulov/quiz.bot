/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:09.07.2024
 * TIME:9:06
 */
package com.telegram_bot.zakovat_bot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "flow")
public class Flow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long answerId;

    private Long questionId;

    private Long userId;

}
