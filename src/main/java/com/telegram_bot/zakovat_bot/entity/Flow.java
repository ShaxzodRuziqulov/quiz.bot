/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:09.07.2024
 * TIME:9:06
 */
package com.telegram_bot.zakovat_bot.entity;

import com.telegram_bot.zakovat_bot.entity.enumerated.Status;
import jakarta.persistence.*;
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
    private String firstName;
    @Enumerated(EnumType.STRING)
    private Status status;

}
