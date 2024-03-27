package com.marco.service;

import com.marco.dto.TextResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public interface TextService {
    TextResponse call(String message);
    Flux<String> generateTextStream(String message);
}