package com.merci.fitapp.controllers;

import com.merci.fitapp.dtos.CreateWorkoutDto;
import com.merci.fitapp.entities.Workout;
import com.merci.fitapp.exception.ServiceException;
import com.merci.fitapp.response.ApiResponse;
import com.merci.fitapp.services.WorkoutService;
import com.merci.fitapp.utils.Mapper;
import com.merci.fitapp.utils.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/workouts")
@RequiredArgsConstructor
public class WorkoutController {
    private final WorkoutService workoutService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Object>> createWorkout(@RequestBody CreateWorkoutDto dto) throws IOException {
        try {
         Workout wrk = workoutService.createWorkout(dto);
         return ResponseEntity.ok(ApiResponse.builder().success(true).data(wrk).build());
        }catch(ServiceException e) {
            return ResponseHandler.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Object>> getAllWorkouts() {
        try{
            List<Workout> wrks = workoutService.getWorkouts();
            return ResponseEntity.ok(ApiResponse.builder().data(wrks).success(true).build());
        }catch (ServiceException e) {
            return ResponseHandler.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteWorkout(@PathVariable("id") Integer id) {
        try{
            String dWrk = workoutService.deleteWorkOut(id);
            return ResponseEntity.ok(ApiResponse.builder().data(dWrk).success(true).build());
        }catch(Exception e){
            return ResponseHandler.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
