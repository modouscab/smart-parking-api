package com.smartcity.parking.ai.controller;

import com.smartcity.parking.ai.AIClient;
import com.smartcity.parking.ai.dto.AIRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.BufferedReader;

import java.io.InputStream;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
//@CrossOrigin(origins = "*", allowedMethods = {"GET", "POST", "OPTIONS"})
public class AIController {
    private final AIClient aiClient;

    public AIController(AIClient aiClient) {
        this.aiClient = aiClient;
    }

    @PostMapping(
            value = "/chat",
            produces = "text/event-stream"
    )
    public SseEmitter chat(
            @RequestBody AIRequest request
    ) {

        SseEmitter emitter = new SseEmitter();

        new Thread(() -> {

            try {

                InputStream inputStream =
                        aiClient.stream(
                                request.getQuestion(),
                                request.getSessionId()
                        );

                byte[] buffer = new byte[32];

                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {

                    String chunk =
                            new String(buffer, 0, bytesRead);

                    emitter.send(chunk);

                    emitter.send(SseEmitter.event().comment("flush"));
                }

                emitter.complete();

            } catch (Exception e) {

                emitter.completeWithError(e);
            }

        }).start();

        return emitter;
    }
}
