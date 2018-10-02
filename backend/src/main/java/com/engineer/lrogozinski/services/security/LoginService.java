package com.engineer.lrogozinski.services.security;

import com.engineer.lrogozinski.dto.security.LoggedUser;

public interface LoginService {
    LoggedUser getLoggedUserInfo(String username);
}
