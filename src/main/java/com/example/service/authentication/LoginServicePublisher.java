package com.example.service.authentication;

import javax.xml.ws.Endpoint;

import com.example.service.authentication.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class LoginServicePublisher {

    @Autowired
    private ServerConfig serverConfig;

    public void publishService() {
        LoginServiceImpl loginService = new LoginServiceImpl();
        // 使用配置类中的属性来发布服务
        Endpoint.publish("http://" + serverConfig.getAddress() + ":" + serverConfig.getPort() + "/login", loginService);
        System.out.println("LoginService is published on http://" + serverConfig.getAddress() + ":" + serverConfig.getPort() + "/login");
    }

    public static void main(String[] args) {
        // 获取Spring应用上下文
        ConfigurableApplicationContext context = SpringApplication.run(LoginServicePublisher.class, args);
        // 从上下文中获取LoginServicePublisher实例并调用publishService方法
        LoginServicePublisher publisher = context.getBean(LoginServicePublisher.class);
        publisher.publishService();
    }
}
