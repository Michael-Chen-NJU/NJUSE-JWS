package com.example.service.wsdl.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("serverConfigA")
@ConfigurationProperties(prefix = "serverb")
public class ServerConfig {
    private String address;
    private int port;

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
}

