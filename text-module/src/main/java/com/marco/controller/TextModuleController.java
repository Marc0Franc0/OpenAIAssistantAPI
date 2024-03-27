package com.marco.controller;

import com.marco.dto.TextResponse;
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
import reactor.core.publisher.Flux;
import com.marco.service.TextService;

@RestController
@RequestMapping("/api/v1")
public class TextModuleController {
    @Autowired
    TextService openAiService;
    @Operation(summary = "Generate a text response using the OpenAI API", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TextResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @GetMapping("/ai/generateText")
    public ResponseEntity<TextResponse> generateAiText(@RequestParam(value = "message", defaultValue = "Hola") String message) {
        // Consulta a OpenAi
        return ResponseEntity.status(HttpStatus.OK).body(openAiService.call(message));
    }
    @Operation(summary = "Generates a stream of text using the OpenAI API", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content( schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @GetMapping("/ai/generateTextStream")
    public Flux<String> generateAiTextStream
            (@RequestParam(value = "message", defaultValue = "Hola") String message) {
        // Consulta a OpenAi
        return openAiService.generateTextStream(message);
    }

}
