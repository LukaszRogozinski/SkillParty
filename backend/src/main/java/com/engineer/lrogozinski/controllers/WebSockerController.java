package com.engineer.lrogozinski.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class WebSockerController {

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    @MessageMapping("/sportMessage")
    @SendTo("/sportTopic/reply")
    public String processSportMessageFromClient(@Payload String message) throws Exception {

        return message;
    }

    @MessageMapping("/relaxMessage")
    @SendTo("/relaxTopic/reply")
    public String processRelaxMessageFromClient(@Payload String message) throws Exception {
            //String mes = message;
        return message;
    }

    @MessageExceptionHandler
    public String handleException(Throwable exception) {
        simpMessageSendingOperations.convertAndSend("/errors", exception.getMessage());
        return exception.getMessage();
    }

}
