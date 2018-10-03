package com.engineer.lrogozinski.services.security.impl;

import com.engineer.lrogozinski.services.security.EncryptionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public EncryptionServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String encryptString(String input) {
            return bCryptPasswordEncoder.encode(input);
        }

    public boolean checkPassword(String plainPassword, String encryptedPassword) {
        return  bCryptPasswordEncoder.matches(plainPassword, encryptedPassword);
    }
}

