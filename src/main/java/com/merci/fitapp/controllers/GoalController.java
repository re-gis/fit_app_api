package com.merci.fitapp.controllers;

import com.merci.fitapp.dtos.CreateGoalDto;
import com.merci.fitapp.dtos.UpdateGoalDto;
import com.merci.fitapp.entities.FitnessGoal;
import com.merci.fitapp.exception.ServiceException;
import com.merci.fitapp.response.ApiResponse;
import com.merci.fitapp.services.GoalService;
import com.merci.fitapp.utils.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/goals")
public class GoalController {
    private final GoalService goalService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Object>> createGoal(@RequestBody CreateGoalDto dto) {
        try{
            String gSaved = goalService.CreateGoal(dto);
            return ResponseEntity.ok(ApiResponse.builder().success(true).data(gSaved).build());
        }catch(ServiceException e){
            return ResponseHandler.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Object>> getAllYourGoals() {
        try{
            List<FitnessGoal> goals = goalService.getALlYouGoals();
            return ResponseEntity.ok(ApiResponse.builder().success(true).data(goals).build());
        }catch(ServiceException e){
            return  ResponseHandler.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> getOneOfYourGoals(@PathVariable("id") Integer id) {
        try{
            Object o = goalService.getOneOfYourGoals(id);
            return ResponseEntity.ok(ApiResponse.builder().success(true).data(o).build());
        }catch(ServiceException e){
            return ResponseHandler.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteGoal(@PathVariable("id") Integer id) {
        try{
            String dGoal = goalService.deleteGoal(id);
            return ResponseEntity.ok(ApiResponse.builder().success(true).data(dGoal).build());
        }catch(ServiceException e){
            return ResponseHandler.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/complete")
    public ResponseEntity<ApiResponse<Object>> complete(@RequestBody UpdateGoalDto dto) {
        try{
            String cGoal = goalService.completeGoal(dto.getGoalId());
            return ResponseEntity.ok(ApiResponse.builder().success(true).data(cGoal).build());
        }catch(ServiceException e) {
            return ResponseHandler.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/failed")
    public ResponseEntity<ApiResponse<Object>> failed(@RequestBody UpdateGoalDto dto) {
        try{
            String dGoal = goalService.uncompleteGoal(dto.getGoalId());
            return ResponseEntity.ok(ApiResponse.builder().success(true).data(dGoal).build());
        }catch(ServiceException e) {
            return ResponseHandler.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
