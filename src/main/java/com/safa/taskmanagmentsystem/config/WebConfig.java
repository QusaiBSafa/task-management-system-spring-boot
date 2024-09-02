package com.safa.taskmanagmentsystem.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configurable
public class WebConfig implements WebMvcConfigurer {

    private final HTTPCorrelationInterceptor httpCorrelationInterceptor;

    public WebConfig(HTTPCorrelationInterceptor httpCorrelationInterceptor) {
        this.httpCorrelationInterceptor = httpCorrelationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpCorrelationInterceptor);
    }
}
