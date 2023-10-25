package com.merci.fitapp.dtos;

import com.merci.fitapp.entities.Exercise;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateWorkoutDto {

    private String title;
    private int duration;
    private String type;
    private String description;
    private Integer[] exercises;
}
