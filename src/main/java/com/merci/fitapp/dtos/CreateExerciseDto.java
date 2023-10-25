package com.merci.fitapp.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateExerciseDto {
    private String imageUrl;
    private String videoUrl;
    private String name;
}
