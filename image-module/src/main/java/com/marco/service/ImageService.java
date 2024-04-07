package com.marco.service;

import com.marco.dto.ImageResponseDTO;
import org.springframework.ai.image.Image;
import org.springframework.stereotype.Service;

@Service
public interface ImageService {
    ImageResponseDTO generateImage(String message);
}
