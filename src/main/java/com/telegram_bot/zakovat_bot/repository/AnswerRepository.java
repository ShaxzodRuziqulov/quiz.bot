/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:04.07.2024
 * TIME:0:54
 */
package com.telegram_bot.zakovat_bot.repository;

import com.telegram_bot.zakovat_bot.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionId(Long questionId);
}
