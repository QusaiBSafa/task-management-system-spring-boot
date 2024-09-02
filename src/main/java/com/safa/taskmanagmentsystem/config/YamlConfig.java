package com.safa.taskmanagmentsystem.config;

import com.safa.taskmanagmentsystem.factory.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application.configuration")
//@PropertySource(value = "classpath:application.yml", factory = YamlPropertySourceFactory.class)
@Getter
@Setter
public class YamlConfig {

    private String name;
    private List<String> aliases;

    private List<String> corsAllowedList = new ArrayList<>();
    private List<String> publicApiList = new ArrayList<>();
}
