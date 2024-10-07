package com.safa.taskmanagmentsystem.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configurable
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final HTTPCorrelationInterceptor httpCorrelationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpCorrelationInterceptor);
    }
}
