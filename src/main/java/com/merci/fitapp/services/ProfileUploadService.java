package com.merci.fitapp.services;

import com.cloudinary.utils.ObjectUtils;
import com.merci.fitapp.config.CloudinaryConfig;
import com.merci.fitapp.entities.User;
import com.merci.fitapp.exception.ServiceException;
import com.merci.fitapp.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProfileUploadService {
    private final UserServiceImpl userService;
    private final UploadPhotoService uploadPhotoService;

    public String uploadProfilePhoto(MultipartFile file) throws IOException, ServiceException {
        try {
            // get user
            User user = userService.getLoggedUser();
            String url = uploadPhotoService.uploadProfilePic(file);
            user.setImageUrl((String) url);
            userService.saveUser(user);
            return url;
        }catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }

    }

}
