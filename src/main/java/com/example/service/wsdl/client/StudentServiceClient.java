package com.example.service.wsdl.client;

import com.example.service.wsdl.*;
import com.example.service.wsdl.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
@Component
public class StudentServiceClient {

    // 这里假设ServerConfig可以正常获取配置信息，实际中可能需要完善其获取逻辑
    @Autowired
    private ServerConfig serverConfig;

    public static void main(String[] args) {
        // 获取Spring应用上下文
        ConfigurableApplicationContext context = SpringApplication.run(StudentServicePublisher.class, args);
        // 从上下文中获取LoginServiceClient实例
        StudentServiceClient client = context.getBean(StudentServiceClient.class);
        // 1.第一个测试用例：调用学生服务的三个方法，并且全部成功
        try {
            System.out.println("测试用例1：调用学生服务的三个方法，并且全部成功");
            client.callStudentService();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 2.第二个测试用例：登录服务不可用导致签到失败
        System.out.println("--------------------");
        System.out.println("测试用例2：登录服务不可用导致签到失败");
        client.testStudentAttendanceLoginServiceUnavailable();
        // 3.第三个测试用例：登录验证失败导致信息记录失败
        System.out.println("--------------------");
        System.out.println("测试用例3：登录验证失败导致信息记录失败");
        client.testRecordStudentInfoLoginFailure();
        // 4.第四个测试用例：未查询到学生信息
        System.out.println("--------------------");
        System.out.println("测试用例4：未查询到学生信息");
        client.testQueryStudentInfoNotFound();
    }

    public void callStudentService() throws Exception {
        // 根据配置获取服务地址，确保serverConfig相关配置正确加载
        URL url = new URL("http://" + serverConfig.getAddress() + ":" + serverConfig.getPort() + "/student-service");
        QName qname = new QName("http://wsdl.service.example.com/", "StudentServiceImplService");
        Service service = Service.create(url, qname);
        StudentServicePortType studentService = service.getPort(StudentServicePortType.class);

        // 示例1：调用学生签到服务
        AttendanceRequest attendanceRequest = new AttendanceRequest();
        attendanceRequest.setStudentEmail("221250100undergraduate.@nju.edu.cn");
        AttendanceResponse attendanceResponse = studentService.studentAttendance(attendanceRequest);
        System.out.println("签到服务响应: " + attendanceResponse.getMessage());

        // 示例2：调用学生信息记录服务
        StudentInfoRecordRequest recordRequest = new StudentInfoRecordRequest();
        recordRequest.setStudentName("张三");
        recordRequest.setStudentGender("男");
        recordRequest.setStudentId("20240001");
        recordRequest.setBirthDate("2000-01-01");
        recordRequest.setDepartment("计算机学院");
        recordRequest.setGrade("2024级");
        recordRequest.setStudentEmail("zhangsan.undergraduate@nju.edu.cn");
        StudentInfoRecordResponse recordResponse = studentService.recordStudentInfo(recordRequest);
        System.out.println("学生信息记录服务响应: " + recordResponse.getRecordMessage());

        // 示例3：调用学生信息查询服务
        StudentInfoQueryRequest queryRequest = new StudentInfoQueryRequest();
        queryRequest.setQueryKey("zhangsan.undergraduate@nju.edu.cn");
        StudentInfoQueryResponse queryResponse = studentService.queryStudentInfo(queryRequest);
        if (queryResponse.isQueryStatus()) {
            System.out.println("查询到学生信息: ");
            System.out.println("姓名: " + queryResponse.getStudentName());
            System.out.println("性别: " + queryResponse.getStudentGender());
            System.out.println("学号: " + queryResponse.getStudentId());
            System.out.println("出生日期: " + queryResponse.getBirthDate());
            System.out.println("院系: " + queryResponse.getDepartment());
            System.out.println("年级: " + queryResponse.getGrade());
        } else {
            System.out.println("学生信息查询服务响应: " + queryResponse.isQueryStatus());
        }
    }

    // 测试登录服务不可用导致签到失败的情况（模拟创建登录服务代理时返回null）
    public void testStudentAttendanceLoginServiceUnavailable() {
        try {
            // 根据配置获取服务地址，确保serverConfig相关配置正确加载
            URL url = new URL("http://" + serverConfig.getAddress() + ":" + serverConfig.getPort() + "/student-service");
            QName qname = new QName("http://wsdl.service.example.com/", "StudentServiceImplService");
            Service service = Service.create(url, qname);
            StudentServicePortType studentService = service.getPort(StudentServicePortType.class);

            // 示例1：调用学生签到服务
            AttendanceRequest attendanceRequest = new AttendanceRequest();
            attendanceRequest.setStudentEmail("221250100undergraduate.@example.com");
            AttendanceResponse attendanceResponse = studentService.studentAttendance(attendanceRequest);
            System.out.println("签到服务响应: " + attendanceResponse.getMessage());

            if (! attendanceResponse.isStatus()) {
                System.out.println("签到失败，符合预期，错误消息: " + attendanceResponse.getMessage());
            } else {
                System.out.println("签到成功，但预期应为失败，测试不通过");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("出现异常，可能是服务连接等问题，测试不通过");
        }
    }

    // 测试登录验证失败导致信息记录失败的情况（模拟登录返回结果不包含"成功"字样）
    public void testRecordStudentInfoLoginFailure() {
        try {
            URL url = new URL("http://" + serverConfig.getAddress() + ":" + serverConfig.getPort() + "/student-service");
            QName qname = new QName("http://wsdl.service.example.com/", "StudentServiceImplService");
            Service service = Service.create(url, qname);
            StudentServicePortType studentService = service.getPort(StudentServicePortType.class);

            StudentInfoRecordRequest recordRequest = new StudentInfoRecordRequest();
            recordRequest.setStudentName("李四");
            recordRequest.setStudentGender("女");
            recordRequest.setStudentId("20240002");
            recordRequest.setBirthDate("2001-02-02");
            recordRequest.setDepartment("数学系");
            recordRequest.setGrade("2024级");
            recordRequest.setStudentEmail("lisi@example.com");

            StudentInfoRecordResponse response = studentService.recordStudentInfo(recordRequest);

            if (!response.isRecordStatus()) {
                System.out.println("信息记录失败，符合预期，错误消息: " + response.getRecordMessage());
            } else {
                System.out.println("信息记录成功，但预期应为失败，测试不通过");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("出现异常，可能是服务连接等问题，测试不通过");
        }
    }

    // 测试未查询到学生信息的情况
    public void testQueryStudentInfoNotFound() {
        try {
            URL url = new URL("http://" + serverConfig.getAddress() + ":" + serverConfig.getPort() + "/student-service");
            QName qname = new QName("http://wsdl.service.example.com/", "StudentServiceImplService");
            Service service = Service.create(url, qname);
            StudentServicePortType studentService = service.getPort(StudentServicePortType.class);

            StudentInfoQueryRequest queryRequest = new StudentInfoQueryRequest();
            queryRequest.setQueryKey("nonexistent@example.com");

            StudentInfoQueryResponse response = studentService.queryStudentInfo(queryRequest);

            if (!response.isQueryStatus()) {
                System.out.println("查询失败，符合预期，错误消息: " + response.isQueryStatus());
            } else {
                System.out.println("查询成功，但预期应为失败，测试不通过");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("出现异常，可能是服务连接等问题，测试不通过");
        }
    }
}