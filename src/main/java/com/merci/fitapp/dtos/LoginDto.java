package com.merci.fitapp.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDto {
    private String email;

    private String password;
}
