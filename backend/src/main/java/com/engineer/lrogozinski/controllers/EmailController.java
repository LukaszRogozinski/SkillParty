package com.engineer.lrogozinski.controllers;

import com.engineer.lrogozinski.config.EmailConfig;
import com.engineer.lrogozinski.dto.EmailDto;
import com.engineer.lrogozinski.services.EmailService;
import com.engineer.lrogozinski.services.EventCategoryService;
import com.engineer.lrogozinski.services.UserDataService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController( EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping("/send")
    public void sendFeedback(@RequestBody EmailDto emailDto) {
        emailService.sendEmail(emailDto);
    }
}
