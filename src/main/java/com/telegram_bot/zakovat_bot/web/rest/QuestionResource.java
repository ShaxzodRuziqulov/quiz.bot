/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:09.07.2024
 * TIME:18:59
 */
package com.telegram_bot.zakovat_bot.web.rest;

import com.telegram_bot.zakovat_bot.entity.Answer;
import com.telegram_bot.zakovat_bot.entity.Question;
import com.telegram_bot.zakovat_bot.service.QuestionService;
import com.telegram_bot.zakovat_bot.service.dto.AnswerDto;
import com.telegram_bot.zakovat_bot.service.dto.QuestionDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionResource {
    public final QuestionService questionService;

    public QuestionResource(QuestionService questionService) {
        this.questionService = questionService;
    }
    @PostMapping("/question/create")
    public ResponseEntity<?> create(@RequestBody QuestionDto questionDto) throws URISyntaxException {
        QuestionDto result = questionService.create(questionDto);
        return ResponseEntity
                .created(new URI("api/question/create" + result.getId()))
                .body(result);
    }

    @PutMapping("/question/update")
    public ResponseEntity<?> update(@RequestBody QuestionDto questionDto, @PathVariable Long id) throws URISyntaxException {
        if (questionDto.getId() == null || !questionDto.getId().equals(id)) {
            return ResponseEntity.badRequest().body("Invalid ID");
        }
        try {
            QuestionDto result = questionService.create(questionDto);
            return ResponseEntity.ok().body(result);
        } catch (
                EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/question/findAll")
    public ResponseEntity<?> findAll() {
        List<Question> findAll = questionService.findAllQuestions();
        return ResponseEntity.ok(findAll);
    }

    @GetMapping("/question/by/{id}")
    public ResponseEntity<?> byId(@PathVariable Long id) {
        Question question = questionService.getQuestionById(id);
        return ResponseEntity.ok(question);
    }

    @DeleteMapping("/question/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        questionService.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}
