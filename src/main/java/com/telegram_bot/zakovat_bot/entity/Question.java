/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:04.07.2024
 * TIME:0:22
 */
package com.telegram_bot.zakovat_bot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionText;


}
