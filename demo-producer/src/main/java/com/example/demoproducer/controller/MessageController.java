package com.example.demoproducer.controller;

import com.example.demoproducer.model.Message;
import com.example.demoproducer.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message){

        messageService.send(message.getText());
        return ResponseEntity.ok(message);
    }
}
