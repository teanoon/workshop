package com.example.research.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.example.research.service")
@EnableJpaRepositories("com.example.research.repository")
@EnableTransactionManagement
@EntityScan("com.example.research.model")
@Import({DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class CoreConfiguration {

}
