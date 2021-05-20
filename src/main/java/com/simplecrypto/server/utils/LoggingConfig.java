package com.simplecrypto.server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class LoggingConfig {

    @Bean
    @Scope("prototype")
    public Logger slf4jLogger(InjectionPoint injectionPoint) {
        Class<?> targetClass = injectionPoint.getMember().getDeclaringClass();
        Logger logger = LoggerFactory.getLogger(targetClass);
        return logger;
    }
}