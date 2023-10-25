package com.merci.fitapp.controllers;

import com.merci.fitapp.dtos.CreateExerciseDto;
import com.merci.fitapp.entities.Exercise;
import com.merci.fitapp.exception.ServiceException;
import com.merci.fitapp.response.ApiResponse;
import com.merci.fitapp.services.ExerciseService;
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
@RequestMapping("/api/v1/exercises")
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseService exerciseService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Object>> createExercise(@RequestParam("photo") MultipartFile photo, @RequestParam("video") MultipartFile video, @RequestParam("details") String details) throws ServiceException, IOException {
        try{
            // map the request
            CreateExerciseDto dto = Mapper.getExerciseDtoFromRequest(details);
            Exercise e = exerciseService.createExercise(dto, photo, video);
            return ResponseEntity.ok(ApiResponse.builder().data(e).success(true).build());
        }catch (ServiceException e) {
            return ResponseHandler.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Object>> getAllExercises() throws ServiceException {
        try{
            List<Exercise> exs = exerciseService.getExercises();
            return ResponseEntity.ok(ApiResponse.builder().data(exs).success(true).build());
        } catch (ServiceException e){
            return ResponseHandler.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> getOneExercise(@PathVariable("id") Integer id) {
        try{
            Exercise ex = exerciseService.getSingleExercise(id);
            return ResponseEntity.ok(ApiResponse.builder().success(true).data(ex).build());
        }catch (ServiceException e){
            return ResponseHandler.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
