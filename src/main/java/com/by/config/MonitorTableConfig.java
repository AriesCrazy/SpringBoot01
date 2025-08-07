package com.by.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "monitor")
@Data
public class MonitorTableConfig {
    private Map<String, List<String>> tables;
}
