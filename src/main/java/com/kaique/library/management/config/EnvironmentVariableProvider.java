package com.kaique.library.management.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class EnvironmentVariableProvider {

    @Value("${default.admin.name}")
    private String adminName;

    @Value("${default.admin.email}")
    private String adminEmail;

    @Value("${default.admin.password}")
    private String adminPassword;

    @Value("${spring.profiles}")
    private String currentEnvironment;

}
