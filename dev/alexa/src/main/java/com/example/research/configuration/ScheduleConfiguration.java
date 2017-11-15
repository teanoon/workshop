package com.example.research.configuration;

import com.example.research.properties.CustomProperties;
import com.example.research.service.WebsiteRankService;
import com.example.research.service.impl.WebsiteRankServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@ComponentScan("com.example.research.schedule")
public class ScheduleConfiguration {

    @Bean
    public CustomProperties properties() throws IOException {
        CustomProperties customProperties = new CustomProperties();
        customProperties.getEntries();
        return customProperties;
    }

}
