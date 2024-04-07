package com.marco.service;

import com.marco.dto.ImageResponseDTO;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageClient;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private OpenAiImageClient openAiImageClient;
    @Override
    public ImageResponseDTO generateImage(String message) {
        String error;
        try{
            ImageResponse generatedImage =
                    openAiImageClient.call(
                            new ImagePrompt(message,
                                    OpenAiImageOptions.builder()
                                            .withQuality("standard")
                                            .withModel("dall-e-2")
                                            .withN(5)
                                            .withHeight(1024)
                                            .withWidth(1024)
                                            .build()));
           ImageResponseDTO imageResponseDTO = new ImageResponseDTO(message);
            generatedImage.getResults()
                    .forEach(imageGeneration ->
                            imageResponseDTO.getImagesUrl().add(imageGeneration.getOutput().getUrl()));
            return imageResponseDTO;
        } catch (Exception e){
            error=e.getMessage();
        }
        throw new RuntimeException(error);
    }
}
