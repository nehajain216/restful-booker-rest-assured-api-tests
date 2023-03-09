package com.nj.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.InputStream;

@Slf4j
public class ConfigLoader {
    private static final String DEFAULT_CONFIG_FILE = "config.json";
    private static final String ENV_KEY_CONFIG_FILE = "CONFIG_FILE";
    private static final String ENV_KEY_BOOKER_USERNAME = "BOOKER_USERNAME";
    private static final String ENV_KEY_BOOKER_PASSWORD = "BOOKER_PASSWORD";

    private static Configuration config;

    public static Configuration loadConfiguration() {
        if(config != null) {
            return config;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String configFile = DEFAULT_CONFIG_FILE;
        try {
            String configFileOverride = System.getenv(ENV_KEY_CONFIG_FILE);
            log.info("configFileOverride: {}", configFileOverride);
            InputStream inputStream;
            if(configFileOverride != null && !configFileOverride.trim().isEmpty()) {
                configFile = configFileOverride;
                log.info("Loading configuration from file: {}", configFile);
                inputStream = new FileInputStream(configFile);
            } else {
                log.info("Loading default configuration file");
                inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream(configFile);
            }
            config = objectMapper.readValue(inputStream, Configuration.class);
            config.setUsername(System.getenv(ENV_KEY_BOOKER_USERNAME));
            config.setPassword(System.getenv(ENV_KEY_BOOKER_PASSWORD));
            return config;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
