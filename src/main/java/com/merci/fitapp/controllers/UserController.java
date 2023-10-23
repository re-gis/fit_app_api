package com.merci.fitapp.controllers;

import com.merci.fitapp.entities.User;
import com.merci.fitapp.exception.ServiceException;
import com.merci.fitapp.response.ApiResponse;
import com.merci.fitapp.services.UserService;
import com.merci.fitapp.utils.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<User>> getMe() {
        try {
            return ResponseEntity.ok(new ApiResponse<>(userService.getLoggedUser()));
        }catch (ServiceException e) {
            return ResponseHandler.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/upload-profile")
//    public ResponseEntity<ApiResponse<String>> uploadProfile(HttpServletRequest request)
}
