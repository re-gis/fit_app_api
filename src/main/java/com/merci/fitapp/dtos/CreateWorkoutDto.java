package com.merci.fitapp.dtos;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    private MultipartFile imageUrl;
    private MultipartFile videoUrl;
}
