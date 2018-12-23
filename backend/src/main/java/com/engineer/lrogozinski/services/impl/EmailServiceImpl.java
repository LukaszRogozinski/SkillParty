package com.engineer.lrogozinski.services.impl;

import com.engineer.lrogozinski.config.EmailConfig;
import com.engineer.lrogozinski.domain.EventCategory;
import com.engineer.lrogozinski.dto.EmailDto;
import com.engineer.lrogozinski.services.EmailService;
import com.engineer.lrogozinski.services.EventCategoryService;
import com.engineer.lrogozinski.services.UserDataService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailConfig emailConfig;

    private final EventCategoryService eventCategoryService;

    private final UserDataService userDataService;

    public EmailServiceImpl(EmailConfig emailConfig, EventCategoryService eventCategoryService, UserDataService userDataService) {
        this.emailConfig = emailConfig;
        this.eventCategoryService = eventCategoryService;
        this.userDataService = userDataService;
    }

    @Override
    public void sendEmail(EmailDto emailDto) {
        //Create a mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailConfig.getHost());
        mailSender.setPort(this.emailConfig.getPort());
        mailSender.setUsername(this.emailConfig.getUsername());
        mailSender.setPassword(this.emailConfig.getPassword());

        EventCategory eventCategory = eventCategoryService.findByName(emailDto.getEventCategory());
        List<String> userDataList = userDataService.findAllEmailsByFavouriteEventCategoriesContains(eventCategory);
        //Create an email instance
        for (String email:userDataList) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(emailDto.getEmail());
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject(emailDto.getSubject());
            simpleMailMessage.setText(emailDto.getFeedback());

            //Send mail
            mailSender.send(simpleMailMessage);
        }
    }
}
