package com.example.service.wsdl;

import com.example.service.authentication.LoginServiceImpl;
import com.example.service.wsdl.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.xml.ws.Endpoint;

@SpringBootApplication
@Component
public class StudentServicePublisher {
    @Autowired
    private ServerConfig serverConfig;
    public void publishService() {
        StudentServiceImpl studentService = new StudentServiceImpl();
        // 使用配置类中的属性来发布服务
        Endpoint.publish("http://" + serverConfig.getAddress() + ":" + serverConfig.getPort() + "/student", studentService);
        System.out.println("StudentService is published on http://" + serverConfig.getAddress() + ":" + serverConfig.getPort() + "/student");
    }
    public static void main(String[] args) {
        // 获取Spring应用上下文
        ConfigurableApplicationContext context = SpringApplication.run(StudentServicePublisher.class, args);
        // 从上下文中获取LoginServicePublisher实例并调用publishService方法
        StudentServicePublisher publisher = context.getBean(StudentServicePublisher.class);
        publisher.publishService();
    }
}