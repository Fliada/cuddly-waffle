package com.example.springbasiccourseproject82;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.springbasiccourseproject82")
public class SpringConfig {

    @Bean(name = "engine")
    public ModuleEngine provideModuleEngine() {
        return new ModuleEngine();
    }

}