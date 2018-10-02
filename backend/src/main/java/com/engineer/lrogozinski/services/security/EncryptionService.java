package com.engineer.lrogozinski.services.security;

public interface EncryptionService {

    String encryptString(String input);

    boolean checkPassword(String plainPassword, String encryptedPassword);
}
