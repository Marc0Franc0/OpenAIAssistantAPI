package com.marco.service;

import org.springframework.ai.openai.OpenAiAudioTranscriptionClient;
import org.springframework.ai.openai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.openai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AudioServiceImpl implements AudioService{
    @Autowired
    private OpenAiAudioTranscriptionClient openAiTranscriptionClient;
    @Override
    public String transcribeAudio(MultipartFile multipartFile) {
        Resource audioFile = multipartFile.getResource();
        AudioTranscriptionPrompt transcriptionRequest = new AudioTranscriptionPrompt(audioFile);
        AudioTranscriptionResponse response = openAiTranscriptionClient.call(transcriptionRequest);
        return response.getResult().getOutput();
    }
}
