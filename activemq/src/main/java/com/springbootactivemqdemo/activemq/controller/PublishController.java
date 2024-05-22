package com.springbootactivemqdemo.activemq.controller;

import com.springbootactivemqdemo.activemq.model.SystemMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {

    private final JmsTemplate jmsTemplate;

    public PublishController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @PostMapping("/publishMessage")
    public ResponseEntity<String> publishMessage(@RequestBody SystemMessage systemMessage) {
        try {
            jmsTemplate.convertAndSend("activemq-queue", systemMessage);

            return new ResponseEntity<>("Sent", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
