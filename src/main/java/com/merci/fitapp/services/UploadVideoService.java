package com.merci.fitapp.services;

import com.cloudinary.Cloudinary;
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
public class UploadVideoService {
    private final CloudinaryConfig cloudinaryConfig;

    public String uploadVideo(MultipartFile video) throws IOException, ServiceException {
            if (video == null || video.isEmpty()) {
                throw new ServiceException("Video must not be null");
            }

            Cloudinary cloudinary = cloudinaryConfig.cloudinary();
            Map<?, ?> uploadResult;

            try {
                uploadResult = cloudinary.uploader().uploadLarge(video.getBytes(),
                        ObjectUtils.asMap("resource_type", "video"));
            } catch (Exception e) {
                throw new ServiceException("Failed to upload the video: " + e.getMessage());
            }

            return (String) uploadResult.get("url");
    }
}
