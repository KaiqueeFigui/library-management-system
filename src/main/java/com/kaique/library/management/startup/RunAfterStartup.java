package com.kaique.library.management.startup;

import com.kaique.library.management.config.EnvironmentVariableProvider;
import com.kaique.library.management.entity.Role;
import com.kaique.library.management.entity.User;
import com.kaique.library.management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class RunAfterStartup {


    private final UserRepository userRepository;
    private final EnvironmentVariableProvider environmentVariableProvider;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void createAdminUserFromEnvironmentVariables() {
        var alreadyExistsAdmin = userRepository.existsByRoleDescription("ADMIN");
        if (alreadyExistsAdmin) return;

        var adminUser = User.builder()
                .email(environmentVariableProvider.getAdminEmail())
                .role(new Role(1L))
                .name(environmentVariableProvider.getAdminName())
                .password(passwordEncoder.encode(environmentVariableProvider.getAdminPassword()))
                .build();

        userRepository.saveAndFlush(adminUser);
    }
}
