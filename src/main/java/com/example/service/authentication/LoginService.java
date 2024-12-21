package com.example.service.authentication;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public interface LoginService {
    @WebMethod
    String login(String email);
}