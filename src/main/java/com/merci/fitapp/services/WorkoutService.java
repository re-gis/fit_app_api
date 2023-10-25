package com.merci.fitapp.services;

import com.merci.fitapp.dtos.CreateWorkoutDto;
import com.merci.fitapp.entities.Workout;
import com.merci.fitapp.exception.ServiceException;
import com.merci.fitapp.repositories.ExerciseRepository;
import com.merci.fitapp.repositories.WorkoutRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final UploadPhotoService uploadPhotoService;
    private final UploadVideoService uploadVideoService;


    public Workout createWorkout(CreateWorkoutDto dto) throws IOException {
        if(dto.getDuration() == 0 || dto.getType() == "" || dto.getTitle() == "" || dto.getDescription() == "" || dto.getExercises() == null) {
            throw  new ServiceException("All workout details are required!");
        }

        System.out.println(dto.getExercises());

        Workout wrk = null;
        return  wrk;

    }

}
