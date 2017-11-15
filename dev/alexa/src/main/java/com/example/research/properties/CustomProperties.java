package com.example.research.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CustomProperties {

    private final String CUSTOM_PROPERTIES = "custom.properties";
    private Map<String, String> entries;

    public Map<String, String> getEntries() {
        return entries == null ? loadProperties() : entries;
    }

    private Map<String, String> loadProperties() {
        Properties prop = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream(CUSTOM_PROPERTIES);
        try {
            prop.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //noinspection unchecked
        entries = new HashMap<String, String>((Map)prop);
        return entries;
    }

}
