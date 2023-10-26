package com.merci.fitapp.services;

import com.merci.fitapp.dtos.CreateExerciseDto;
import com.merci.fitapp.entities.Exercise;
import com.merci.fitapp.entities.User;
import com.merci.fitapp.exception.ServiceException;
import com.merci.fitapp.repositories.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final UploadVideoService uploadVideoService;
    private final UploadPhotoService uploadPhotoService;
    private final UserService userService;

    // create an exercise
    public Exercise createExercise(CreateExerciseDto dto, MultipartFile imageUrl, MultipartFile videoUrl) throws IOException, ServiceException {
        if(dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new ServiceException("Exercise name is required");
        }

        // check if user is allowed
        User user = userService.getLoggedUser();
        if(user.getRole().equalsIgnoreCase("user")) {
            throw new ServiceException("Not authorized to perform this action");
        }

        // upload the exercise files
        String photo = (String) uploadPhotoService.uploadProfilePic(imageUrl);
        String video = (String) uploadVideoService.uploadVideo(videoUrl);

        if((photo == null || photo.trim().isEmpty()) && (video == null || video.trim().isEmpty())) {
            throw new ServiceException("Error while uploading video and photo");
        }

        Exercise exercise = new Exercise();
                exercise.setName(dto.getName());
                exercise.setImageUrl(photo);
                exercise.setVideoUrl(video);

                return exerciseRepository.save(exercise);
    }

    public List<Exercise> getExercises() {
        User user = userService.getLoggedUser();
        List<Exercise> exerciseList = exerciseRepository.findAll();
        if(exerciseList.size() == 0) {
            throw new ServiceException("No exercises found for " + user.getName());
        }
        return exerciseList;
    }

    public Exercise getSingleExercise(Integer id) throws ServiceException{
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(() -> new ServiceException("Could not find the exercise!"));

        return exercise;
    }

    public String deleteExercise(int id) throws ServiceException{
        User user = userService.getLoggedUser();

        if(user.getRole().equalsIgnoreCase("user")) {
            throw new ServiceException("Not authorised to perform this actions!");
        }

        // check if it exists
        if(!exerciseRepository.findById(id).isPresent()) {
            throw new ServiceException("Exercise not found!");
        }

        // delete the exercise
        exerciseRepository.deleteById(id);
        return "Exercise deleted successfully!";
    }
}
