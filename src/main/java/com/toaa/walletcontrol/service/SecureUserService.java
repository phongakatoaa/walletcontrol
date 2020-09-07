package com.toaa.walletcontrol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class SecureUserService {
    @Autowired
    private UserService userService;

    private User getCurrentSecureUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public com.toaa.walletcontrol.model.login.User getCurrentUser() {
        return userService.findUserByUsername(getCurrentSecureUser().getUsername());
    }
}
