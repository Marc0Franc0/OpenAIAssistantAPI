package com.marco.controller;

import com.marco.dto.ImageResponseDTO;
import com.marco.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ImageModuleController {
    @Autowired
    private ImageService imageService;
    @Operation(summary = "Generates a images using the OpenAI API", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ImageResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @GetMapping("/ai/generateImage")
    public ResponseEntity<ImageResponseDTO> generateImage
            (@RequestParam(value = "message",defaultValue = "Imagen de ejemplo") String message){
    return ResponseEntity.status(HttpStatus.OK).body(imageService.generateImage(message));
    }
}
