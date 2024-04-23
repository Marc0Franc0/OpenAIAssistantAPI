package com.marco.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AudioService {
    String transcribeAudio(MultipartFile multipartFile);
}
