package com.by.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "email")
@Data
public class EmailConfig {
    private String host;
    private int port;
    private boolean auth;
    private String from;
    private String user;
    private String pass;
    private boolean sslEnable;
}
