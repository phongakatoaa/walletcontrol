package com.toaa.walletcontrol.startup;

import com.toaa.walletcontrol.database.RoleRepository;
import com.toaa.walletcontrol.model.login.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WCCommandLineRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(WCCommandLineRunner.class);
    private static final String[] roleNames = new String[]{"USER", "ADMIN"};

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Checking roles data...");
        for (String roleName : roleNames) {
            Role role = roleRepository.findByRole(roleName);
            if (role == null) {
                logger.error("Role " + roleName + " does not exists! Creating a new one...");
                role = new Role();
                role.setRole("USER");
                roleRepository.save(role);
                logger.info("Role " + roleName + " created.");
            } else {
                logger.info("Role " + roleName + " already exists.");
            }
        }
        logger.info("Roles checking completed...");
    }
}
