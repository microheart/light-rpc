package com.iknowers.rpc.example.server;


import com.iknowers.rpc.example.server.config.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOGGER.debug("start server");
        new AnnotationConfigApplicationContext(ApplicationConfig.class);
    }
}
