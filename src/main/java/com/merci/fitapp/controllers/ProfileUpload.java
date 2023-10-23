package com.merci.fitapp.controllers;

import com.cloudinary.utils.ObjectUtils;
import com.merci.fitapp.config.CloudinaryConfig;
import com.merci.fitapp.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProfileUpload {
    private final CloudinaryConfig cloudinary;

    public String uploadProfilePic(MultipartFile file) throws IOException, ServiceException {
        Map<?, ?> uploadResult = cloudinary.cloudinary().uploader().upload(file.getBytes(), ObjectUtils.emptyMap()              );
        return (String) uploadResult.get("url");
    }
}
