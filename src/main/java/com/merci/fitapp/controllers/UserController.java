package com.merci.fitapp.controllers;

import com.merci.fitapp.dtos.UpdateHeight;
import com.merci.fitapp.dtos.UpdateWeight;
import com.merci.fitapp.entities.User;
import com.merci.fitapp.exception.ServiceException;
import com.merci.fitapp.response.ApiResponse;
import com.merci.fitapp.services.ProfileUploadService;
import com.merci.fitapp.services.UserService;
import com.merci.fitapp.utils.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.rmi.ServerException;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ProfileUploadService profileUploadService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<User>> getMe() {
        try {
            return ResponseEntity.ok(new ApiResponse<>(userService.getLoggedUser()));
        }catch (ServiceException e) {
            return ResponseHandler.error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/upload-profile")
    public ResponseEntity<ApiResponse<String>> uploadProfile(@RequestParam("file")MultipartFile file) throws IOException {
        try {
            String url = profileUploadService.uploadProfilePhoto(file);
            return ResponseEntity.ok(ApiResponse.<String>builder().data(url).success(true).build());
        }catch(ServiceException e) {
            return ResponseHandler.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/update-weight")
    public ResponseEntity<ApiResponse<String>> updateWeight(@RequestBody UpdateWeight dto){
        try{
            String newWeight = userService.updateWeight(dto.getWeight());
            return ResponseEntity.ok(ApiResponse.<String>builder().data(newWeight).success(true).build());
        }catch (ServiceException e) {
            return ResponseHandler.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/update-height")
    public ResponseEntity<ApiResponse<String>> updateHeight(@RequestBody UpdateHeight dto){
        try{
            String newHeight = userService.updateHeight(dto.getHeight());
            return ResponseEntity.ok(ApiResponse.<String>builder().data(newHeight).success(true).build());
        }catch (ServiceException e) {
            return ResponseHandler.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
