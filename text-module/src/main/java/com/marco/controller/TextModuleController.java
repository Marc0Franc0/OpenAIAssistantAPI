package com.marco.controller;

import com.marco.dto.TextResponse;
import com.marco.service.AudioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.ai.openai.OpenAiAudioTranscriptionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import com.marco.service.TextService;

@RestController
@RequestMapping("/api/v1")
public class TextModuleController {
    @Autowired
    private OpenAiAudioTranscriptionClient openAiTranscriptionClient;
    @Autowired
    private AudioService audioService;
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
                    content = @Content( schema = @Schema(implementation = Flux.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @GetMapping("/ai/generateTextStream")
    public Flux<String> generateAiTextStream
            (@RequestParam(value = "message", defaultValue = "Hola") String message) {
        // Consulta a OpenAi
        return openAiService.generateTextStream(message);
    }
    @Operation(summary = "Generate a text response using the OpenAI API", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content( schema = @Schema(implementation = TextResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @PostMapping(value = "/ai/generateText/audio",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TextResponse> generateAiTextFromAudio( @RequestPart(name = "audio") MultipartFile file){
        String str = audioService.transcribeAudio(file);
        return ResponseEntity.status(HttpStatus.OK).body(openAiService.call(str));
    }
    @Operation(summary = "Generates a stream of text using the OpenAI API", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content( schema = @Schema(implementation = Flux.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @PostMapping(value ="/ai/generateTextStream/audio",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Flux<String> generateAiTextStreamFromAudio(@RequestPart(name = "audio") MultipartFile file){
        String str = audioService.transcribeAudio(file);
        return openAiService.generateTextStream(str);
    }

}
