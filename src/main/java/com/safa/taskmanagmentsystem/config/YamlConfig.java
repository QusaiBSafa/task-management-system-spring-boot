package com.safa.taskmanagmentsystem.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * This configuration file make it easier to read properties in components by autowiring this class
 * PropertySource is not used here and is needed in case you want to add additional properties file
 * ex: @PropertySource(value = "classpath:application.yml", factory = YamlPropertySourceFactory.class)
 * The properties should be added under prefix (application.configuration) to be read using this class
 * you can define new property by adding it to application.yml and adding its variable here with the same name
 *
 * @author qusaisafa
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application.configuration")
@Getter
@Setter
public class YamlConfig {

    private String name;
    private List<String> aliases;

    private List<String> corsAllowedList = new ArrayList<>();
    private List<String> publicApiList = new ArrayList<>();
}
