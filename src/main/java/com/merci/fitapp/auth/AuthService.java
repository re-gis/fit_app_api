package com.merci.fitapp.auth;

import com.merci.fitapp.dtos.LoginDto;
import com.merci.fitapp.dtos.RegisterDto;
import com.merci.fitapp.entities.User;
import com.merci.fitapp.exception.ServiceException;
import com.merci.fitapp.repositories.UserRepository;
import com.merci.fitapp.response.ApiResponse;
import com.merci.fitapp.services.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ApiResponse register(RegisterDto dto) {
        if (dto.getAge() == 0 || dto.getEmail() == null || dto.getPassword() == null || dto.getName() == null) {
            throw new ServiceException("All credentials are required!");
        }
        if(userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw  new ServiceException("User already registered");
        }

        var user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .age(dto.getAge())
                .height(dto.getHeight())
                .weight(dto.getWeight())
                .build();

        userRepository.save(user);
        var token = jwtService.generateToken(user);

        return ApiResponse.builder()
                .data(token)
                .success(true)
                .build();

    }

    public ApiResponse loginUser(LoginDto dto) {
        if (dto.getEmail() == null || dto.getPassword() == null) {
            throw new ServiceException("All credentials are required!");
        }

        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        if(!auth.isAuthenticated()) {
            throw  new ServiceException("Authentication failed");
        }

        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new ServiceException("User not found!"));

        System.out.println(user);
        var token = jwtService.generateToken(user);
        return ApiResponse.builder()
                .success(true)
                .data(token)
                .build();
    }
}
