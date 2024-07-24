/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:09.07.2024
 * TIME:18:48
 */
package com.telegram_bot.zakovat_bot.web.rest;

import com.telegram_bot.zakovat_bot.entity.Answer;
import com.telegram_bot.zakovat_bot.service.AnswerService;
import com.telegram_bot.zakovat_bot.service.dto.AnswerDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AnswerResource {
    private final AnswerService answerService;

    public AnswerResource(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/answer/create")
    public ResponseEntity<?> create(@RequestBody AnswerDto answerDto) throws URISyntaxException {
        AnswerDto result = answerService.create(answerDto);
        return ResponseEntity
                .created(new URI("api/answer/create" + result.getId()))
                .body(result);
    }

    @PutMapping("/answer/update/{id}")
    public ResponseEntity<?> update(@RequestBody AnswerDto answerDto, @PathVariable Long id) throws URISyntaxException {
        if (answerDto.getId() == null || !answerDto.getId().equals(id)) {
            return ResponseEntity.badRequest().body("Invalid ID");
        }
        try {
            AnswerDto result = answerService.update(answerDto);
            return ResponseEntity.ok().body(result);
        } catch (
                EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/answer/findAll")
    public ResponseEntity<?> findAll() {
        List<Answer> findAll = answerService.findAllAnswers();
        return ResponseEntity.ok(findAll);
    }

    @GetMapping("/answer/by/{id}")
    public ResponseEntity<?> byId(@PathVariable Long id) {
        Answer answer = answerService.finByIdAnswers(id);
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/answer/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        answerService.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}
