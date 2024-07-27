/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:27.07.2024
 * TIME:16:35
 */
package com.telegram_bot.zakovat_bot.repository;

import com.telegram_bot.zakovat_bot.entity.CorrectAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorrectAnswerRepository extends JpaRepository<CorrectAnswer,Long> {

}
