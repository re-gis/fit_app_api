package com.merci.fitapp.services;

import com.merci.fitapp.entities.Exercise;
import com.merci.fitapp.repositories.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final UploadVideoService uploadVideoService;
    private final UploadPhotoService uploadPhotoService;

    // create an exercise
//    public Exercise createExercise(){
//
//    }
}
