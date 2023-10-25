package com.merci.fitapp.services;

import com.merci.fitapp.dtos.CreateWorkoutDto;
import com.merci.fitapp.entities.Exercise;
import com.merci.fitapp.entities.User;
import com.merci.fitapp.entities.Workout;
import com.merci.fitapp.exception.ServiceException;
import com.merci.fitapp.repositories.ExerciseRepository;
import com.merci.fitapp.repositories.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final UploadPhotoService uploadPhotoService;
    private final UploadVideoService uploadVideoService;
    private final UserService userService;


    public Workout createWorkout(CreateWorkoutDto dto) throws IOException {
        if(dto.getDuration() == 0 || dto.getType() == "" || dto.getTitle() == "" || dto.getDescription() == "" || dto.getExercises() == null) {
            throw  new ServiceException("All workout details are required!");
        }
        User user = userService.getLoggedUser();
        Optional<Workout> existingWorkout = workoutRepository.findByTitleAndUser(dto.getTitle(), user);
        if(existingWorkout.isPresent()) {
            throw new ServiceException("Workout with title '" + dto.getTitle() + "' already exists for the logged-in user!");
        }

        List<Exercise> exercises = new ArrayList<>();
        for (Integer exerciseId : dto.getExercises()) {
            Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(() -> new ServiceException("Exercise with ID " + exerciseId + " does not exist!"));
            exercises.add(exercise);
        }

        // Create and Save Workout
        Workout workout = new Workout();
        workout.setUser(userService.getLoggedUser());
        workout.setTitle(dto.getTitle());
        workout.setDuration(dto.getDuration());
        workout.setType(dto.getType());
        workout.setDescription(dto.getDescription());
        workout.setExercises(exercises);

        return workoutRepository.save(workout);

    }

    public List<Workout> getWorkouts() {
        User user = userService.getLoggedUser();
        List<Workout> workoutList = workoutRepository.findByUser(user);

        if(workoutList.isEmpty()) {
            throw new ServiceException("No workouts found for " + user.getName());
        }

        return workoutList;
    }

    public String deleteWorkOut(Integer id) {
        User user = userService.getLoggedUser();
        // get the workout
        Workout workout = workoutRepository.findById(id).orElseThrow();
        if(workout == null) {
            throw new ServiceException("No workout found!");
        }

        boolean isUserRole = "user".equalsIgnoreCase(user.getRole());
        boolean isNotWorkoutOwner = !user.equals(workout.getUser());

        if (isUserRole && isNotWorkoutOwner) {
            throw new ServiceException("Not authorized to perform this action!");
        }

        // delete the workout
        workoutRepository.deleteById(id);
        return "Workout deleted successfully";
    }

}
