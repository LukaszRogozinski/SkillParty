package com.engineer.lrogozinski.controllers;

import com.engineer.lrogozinski.services.SMSService;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/sms")
public class SMSController {

    private final SMSService smsService;

    public SMSController(SMSService smsService) {
        this.smsService = smsService;
    }

    @RequestMapping(value="/send", method = RequestMethod.POST)
    public void sendSMS(@RequestBody String eventCategory){
        int z = 5;
        this.smsService.sendMessage(eventCategory);
    }
}
