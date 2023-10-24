package com.merci.fitapp.services;

import com.cloudinary.utils.ObjectUtils;
import com.merci.fitapp.config.CloudinaryConfig;
import com.merci.fitapp.entities.User;
import com.merci.fitapp.exception.ServiceException;
import com.merci.fitapp.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UploadPhotoService {
    private final CloudinaryConfig cloudinary;

    public String uploadProfilePic(MultipartFile file) throws IOException, ServiceException {
        if(file == null) {
            throw  new ServiceException("File cannot be null");
        }
        Map<?, ?> uploadResult = cloudinary.cloudinary().uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return (String) uploadResult.get("url");
    }
}
