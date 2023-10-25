package com.merci.fitapp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.merci.fitapp.dtos.CreateExerciseDto;
import com.merci.fitapp.dtos.CreateWorkoutDto;

import java.io.IOException;

public class Mapper {
    public static ObjectMapper objectMapper = new ObjectMapper();

    public static CreateWorkoutDto getCreateWorkoutDtoFromRequest(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, CreateWorkoutDto.class);
        } catch (IOException e) {
            throw new RuntimeException("Error mapping from JSON string to CreateWorkoutDto", e);
        }
    }

    public static CreateExerciseDto getExerciseDtoFromRequest(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, CreateExerciseDto.class);
        }catch (IOException e) {
            throw new RuntimeException("Error mapping from JSON string to CreateWorkoutDto", e);
        }
    }
}
