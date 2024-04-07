package com.marco.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ImageResponseDTO {
    private String request;
    private List<String> imagesUrl;
    public ImageResponseDTO(String request){
        this.request = request;
        this.imagesUrl = new ArrayList<>();
    }
}
