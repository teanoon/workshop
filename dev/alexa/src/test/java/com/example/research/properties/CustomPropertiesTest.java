package com.example.research.properties;

import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomPropertiesTest {

    @Test
    public void getPropertyValue() throws IOException {
        CustomProperties customProperties = new CustomProperties();
        Map<String, String> entries = customProperties.getEntries();

        assertEquals(3,entries.size());
        assertTrue(entries.get("unzipFilePath").equals("/home/jack/test"));
    }

}