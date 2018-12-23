package com.engineer.lrogozinski.services;

import com.engineer.lrogozinski.dto.EmailDto;

public interface EmailService {
    void sendEmail(EmailDto emailDto);
}
