package com.merci.fitapp.auth;

import com.merci.fitapp.dtos.LoginDto;
import com.merci.fitapp.dtos.RegisterDto;
import com.merci.fitapp.exception.ServiceException;
import com.merci.fitapp.response.ApiResponse;
import com.merci.fitapp.utils.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerUser(@Valid @RequestBody RegisterDto dto) {
        try{

            return ResponseEntity.ok(authService.register(dto));
        }catch(ServiceException e) {
            System.out.println(e);
            return ResponseHandler.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginUser(@Valid @RequestBody LoginDto loginDto) {
        try {
            return ResponseEntity.ok(authService.loginUser(loginDto));
        } catch(ServiceException e) {
            return ResponseHandler.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
