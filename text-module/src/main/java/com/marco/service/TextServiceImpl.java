package com.marco.service;

import com.marco.dto.TextResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.Generation;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
@Service
@Slf4j
public class TextServiceImpl implements TextService {
    @Autowired
    OpenAiChatClient chatClient;
    @Override
    public TextResponse call(String message) {
        String response;
        try{
            response = chatClient.call(message);
        }
        catch(Exception e){
            response = "No se obtuvo una respuesta";
            log.debug("Exception:{}".concat(e.getMessage()));
            throw new RuntimeException(response);
        }
        return TextResponse
                .builder()
                .generation(response)
                .build();
    }

    @Override
    public Flux<String> generateTextStream(String message) {
        String responseError;
        try {
            log.info("Streaming initiated with message: {}", message);
            Prompt prompt = new Prompt(new UserMessage(message));
            //buildGeneratedString
            return chatClient.stream(prompt)
                    .map(ChatResponse::getResults)
                    .flatMap(chatResponse -> Flux.fromIterable(chatResponse.stream().toList()))
                    .map(Generation::getOutput)
                    .map(assistantMessage -> {
                        String content=assistantMessage.getContent();
                        if(content==null){
                            content="";
                        }
                        return content;
                    });

        } catch (Exception e) {
            responseError = "No se obtuvo una respuesta";
            log.debug("Exception: {}", e.getMessage());
        }
        throw new RuntimeException(responseError);
    }


}