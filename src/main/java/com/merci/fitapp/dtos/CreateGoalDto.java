package com.merci.fitapp.dtos;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateGoalDto {
    private String description;
    private Date startDate;
    private Date endDate;
    private String goalStatus;
}
