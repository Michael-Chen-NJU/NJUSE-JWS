package com.example.service.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.jws.WebService;
import java.util.Date;


@WebService(endpointInterface = "com.example.service.authentication.LoginService")
public class LoginServiceImpl implements LoginService {
    // 定义一个密钥，用于签名Token，实际应用中应妥善保管
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    // 生成Token的方法
    private String generateToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000); // Token有效期为1小时，可根据需求调整

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
    @Override
    public String login(String email) {
        System.out.println("email: " + email);
        if (email == null || email.isEmpty()) {
            System.err.println("邮箱不能为空");
            return "邮箱不能为空,";
        }
        if (email.endsWith("@nju.edu.cn")) {
            if (email.contains("teacher")) {
                System.out.println("老师身份验证成功");
                String token = generateToken(email);
                return "老师身份验证成功,token:" + token;
            } else if (email.contains("graduate")) {
                System.out.println("研究生身份验证成功");
                String token = generateToken(email);
                return "研究生身份验证成功,token:" + token;
            } else if (email.contains("undergraduate")) {
                System.out.println("本科生身份验证成功");
                String token = generateToken(email);
                return "本科生身份验证成功,token:" + token;
            } else {
                System.err.println("未知身份类型");
                return "未知身份类型,登录失败";
            }
        } else {
            System.err.println("非校内邮箱，登录失败");
            return "非校内邮箱，登录失败";
        }
    }
}