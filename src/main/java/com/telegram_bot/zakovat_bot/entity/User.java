/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:04.07.2024
 * TIME:9:46
 */
package com.telegram_bot.zakovat_bot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity(name = "users_bot")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private String firstName;
    private String lastName;
    private String userName;
    private LocalDateTime registeredAt;
}
