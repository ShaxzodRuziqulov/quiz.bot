/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:04.07.2024
 * TIME:0:53
 */
package com.telegram_bot.zakovat_bot.repository;

import com.telegram_bot.zakovat_bot.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    public String findByQuestionText(String questionText);
}
