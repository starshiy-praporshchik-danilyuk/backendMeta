package com.meta.backend.controller;

import com.meta.backend.dto.EmotionDto;
import com.meta.backend.entity.Emotion;
import com.meta.backend.repo.EmotionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmotionController {

    @Autowired
    private EmotionRepo emotionRepo;

    @PostMapping("/emotion")
    public Emotion addEmotion(@RequestBody EmotionDto emotionDto){
        return emotionRepo.save(Emotion.builder().code(emotionDto.getCode()).build());
    }
}
