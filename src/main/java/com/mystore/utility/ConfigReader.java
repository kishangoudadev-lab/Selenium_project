package com.mystore.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class ConfigReader {

    private final Properties properties;
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);

    public ConfigReader() {
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        // Correct file path using user.dir for project root
        String configPath = System.getProperty("user.dir") + "/Configuration/Config.properties";

        try (FileInputStream fs = new FileInputStream(configPath)) {
            properties.load(fs);
            logger.info("✅ Loaded {} properties from {}", properties.size(), configPath);

            // Optional: print all loaded properties to console (for debugging)
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                logger.debug("{} = {}", entry.getKey(), entry.getValue());
            }

        } catch (IOException e) {
            logger.error("❌ Unable to load Config.properties from {}", configPath, e);
            throw new RuntimeException("Failed to load Config.properties", e);
        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("⚠️ Property '{}' not found in Config.properties", key);
        }
        return value;
    }

    public Properties getProperties() {
        return properties;
    }
}
