package com.toaa.walletcontrol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecureUserService {
    @Autowired
    private UserService userService;

    public com.toaa.walletcontrol.model.login.User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findUserByUsername(auth.getName());
    }
}
