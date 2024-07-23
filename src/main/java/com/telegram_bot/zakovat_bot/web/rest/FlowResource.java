/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:09.07.2024
 * TIME:19:03
 */
package com.telegram_bot.zakovat_bot.web.rest;

import com.telegram_bot.zakovat_bot.entity.Flow;
import com.telegram_bot.zakovat_bot.service.FlowService;
import com.telegram_bot.zakovat_bot.service.dto.FlowDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("api")
public class FlowResource {
    public final FlowService flowService;

    public FlowResource(FlowService flowService) {
        this.flowService = flowService;
    }

    @PostMapping("/flow/create")
    public ResponseEntity<?> create(@RequestBody FlowDto flowDto) throws URISyntaxException {
        FlowDto result = flowService.create(flowDto);
        return ResponseEntity
                .created(new URI("api/answer/create" + result.getId()))
                .body(result);
    }

    @PutMapping("/flow/update/{id}")
    public ResponseEntity<?> update(@RequestBody FlowDto flowDto, @PathVariable Long id) throws URISyntaxException {
        if (flowDto.getId() == null || !flowDto.getId().equals(id)) {
            return ResponseEntity.badRequest().body("Invalid ID");
        }
        try {
            FlowDto result = flowService.create(flowDto);
            return ResponseEntity.ok().body(result);
        } catch (
                EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/flow/findAll")
    public ResponseEntity<?> findAll() {
        List<Flow> findAll = flowService.findAll();
        return ResponseEntity.ok(findAll);
    }

    @GetMapping("/flow/by/{id}")
    public ResponseEntity<?> byId(@PathVariable Long id) {
        Flow flow = flowService.findById(id);
        return ResponseEntity.ok(flow);
    }

    @DeleteMapping("/flow/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        flowService.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}
