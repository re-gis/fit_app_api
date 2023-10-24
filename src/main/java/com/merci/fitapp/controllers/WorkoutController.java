package com.merci.fitapp.controllers;

import com.merci.fitapp.dtos.CreateWorkoutDto;
import com.merci.fitapp.services.WorkoutService;
import com.merci.fitapp.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/workouts")
@RequiredArgsConstructor
public class WorkoutController {
    private final WorkoutService workoutService;

    @PostMapping("/create")
    public void createWorkout(@RequestParam("photo")MultipartFile photo, @RequestParam("video") MultipartFile video, @RequestParam("details") String details) throws IOException {
        // map the details
        CreateWorkoutDto dto = Mapper.getCreateWorkoutDtoFromRequest(details);
        workoutService.createWorkout(dto, photo, video);
    }
}
