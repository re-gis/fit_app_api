package com.merci.fitapp.controllers;

import com.merci.fitapp.dtos.CreateGoalDto;
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
}
