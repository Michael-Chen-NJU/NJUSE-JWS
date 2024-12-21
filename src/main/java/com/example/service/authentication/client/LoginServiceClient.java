package com.example.service.authentication.client;

import com.example.service.authentication.LoginService;
import com.example.service.authentication.LoginServicePublisher;
import com.example.service.authentication.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

@Component
public class LoginServiceClient {

    @Autowired
    private ServerConfig serverConfig;

    public static void main(String[] args) throws Exception {
        // 获取Spring应用上下文
        ConfigurableApplicationContext context = SpringApplication.run(LoginServicePublisher.class, args);
        // 从上下文中获取LoginServiceClient实例
        LoginServiceClient client = context.getBean(LoginServiceClient.class);
        // 调用client的方法来创建和调用服务
        client.callLoginService();
    }

    public void callLoginService() throws Exception {
        // 创建服务的 URL,从ServerConfig中获取内网url
        URL url = new URL("http://" + serverConfig.getAddress() + ":" + serverConfig.getPort() + "/login");

        // 创建服务的 QName
        QName qname = new QName("http://authentication.service.example.com/", "LoginServiceImplService");

        // 创建服务
        Service service = Service.create(url, qname);

        // 获取服务端口
        LoginService loginService = service.getPort(LoginService.class);

        // 调用 login 方法，传入一个有效的校内邮箱
        String response = loginService.login("teacher@nju.edu.cn");
        System.out.println("有效邮箱的响应: " + response);

        // 调用 login 方法，传入一个无效的邮箱
        response = loginService.login("invalid_email@example.com");
        System.err.println("有效邮箱的响应: " + response);
    }
}
