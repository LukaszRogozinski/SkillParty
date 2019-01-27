package com.engineer.lrogozinski.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    @MessageMapping("/sportMessage")
    @SendTo("/sportTopic/reply")
    public String processSportMessage(@Payload String message) throws Exception {
        return message;
    }

    @MessageMapping("/relaxMessage")
    @SendTo("/relaxTopic/reply")
    public String processRelaxMessage(@Payload String message) throws Exception {
        return message;
    }

    @MessageExceptionHandler
    public String handleException(Throwable exception) {
        simpMessageSendingOperations.convertAndSend("/errors", exception.getMessage());
        return exception.getMessage();
    }

}