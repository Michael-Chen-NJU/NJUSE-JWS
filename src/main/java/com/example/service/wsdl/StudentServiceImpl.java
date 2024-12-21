package com.example.service.wsdl;

import com.example.service.authentication.LoginService;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "com.example.service.wsdl.StudentServicePortType")
@Component
public class StudentServiceImpl implements StudentServicePortType {

    private static final String LOGIN_SERVICE_URL = "http://127.0.0.1:8080/login?wsdl";

    // 模拟数据库存储学生信息的列表，实际应用中应替换为与数据库交互的逻辑
    private static final List<StudentInfo> studentInfoList = new ArrayList<>();

    @Override
    public AttendanceResponse studentAttendance(AttendanceRequest request) {
        String studentEmail = request.getStudentEmail();
        // 创建LoginService的客户端代理，调用其login方法进行登录验证
        LoginService loginService = createLoginServiceProxy();
        String loginResult = null;
        if (loginService != null) {
            loginResult = loginService.login(studentEmail);
        }
        // 登录失败，返回相应错误信息
        if (loginResult != null && loginResult.contains("成功")) {
            // 登录成功，执行签到逻辑
            AttendanceResponse response = new AttendanceResponse();
            response.setMessage("签到成功");
            response.setStatus(true);
            return response;
        }
        AttendanceResponse response = new AttendanceResponse();
        response.setMessage("登录失败或签到出现异常：" + loginResult);
        response.setStatus(false);
        return response;
    }

    @Override
    public StudentInfoRecordResponse recordStudentInfo(StudentInfoRecordRequest request) {
        // 创建LoginService的客户端代理，调用其login方法进行登录验证
        LoginService loginService = createLoginServiceProxy();
        String loginResult = null;
        if (loginService != null) {
            loginResult = loginService.login(request.getStudentEmail());
        }
        if (loginResult != null && loginResult.contains("成功")) {
            try {
                // 登录成功，执行记录学生信息逻辑
                StudentInfo studentInfo = new StudentInfo(
                        request.getStudentName(),
                        request.getStudentGender(),
                        request.getStudentId(),
                        request.getBirthDate(),
                        request.getDepartment(),
                        request.getGrade(),
                        request.getStudentEmail()
                );
                studentInfoList.add(studentInfo);

                StudentInfoRecordResponse response = new StudentInfoRecordResponse();
                response.setRecordMessage("学生信息记录成功");
                response.setRecordStatus(true);
                return response;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 登录失败或记录信息出现异常，返回相应错误信息
        StudentInfoRecordResponse response = new StudentInfoRecordResponse();
        response.setRecordMessage("登录失败或学生信息记录出现异常：" + loginResult);
        response.setRecordStatus(false);
        return response;
    }

    @Override
    public StudentInfoQueryResponse queryStudentInfo(StudentInfoQueryRequest request) {
        // 创建LoginService的客户端代理，调用其login方法进行登录验证
        LoginService loginService = createLoginServiceProxy();
        String loginResult = null;
        if (loginService != null) {
            loginResult = loginService.login(request.getQueryKey());
        }
        if (loginResult != null && loginResult.contains("成功")) {
            try {
                // 登录成功，执行查询学生信息逻辑，这里简单模拟根据学号查询（实际可根据更多条件灵活查询）
                for (StudentInfo studentInfo : studentInfoList) {
                    if (studentInfo.getStudentEmail().equals(request.getQueryKey())) {
                        StudentInfoQueryResponse response = new StudentInfoQueryResponse();
                        response.setStudentName(studentInfo.getStudentName());
                        response.setStudentGender(studentInfo.getStudentGender());
                        response.setStudentId(studentInfo.getStudentId());
                        response.setBirthDate(studentInfo.getBirthDate());
                        response.setDepartment(studentInfo.getDepartment());
                        response.setGrade(studentInfo.getGrade());
                        response.setStudentEmail(studentInfo.getStudentEmail());
                        response.setQueryStatus(true);
                        return response;
                    }
                }

                // 如果未查询到信息，返回相应提示
                StudentInfoQueryResponse response = new StudentInfoQueryResponse();
                response.setQueryStatus(false);
                return response;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 登录失败，返回相应错误信息
        StudentInfoQueryResponse response = new StudentInfoQueryResponse();
        response.setQueryStatus(false);
        return response;
    }

    private LoginService createLoginServiceProxy() {
        try {
            URL url = new URL(LOGIN_SERVICE_URL);
            QName qname = new QName("http://authentication.service.example.com/", "LoginServiceImplService");
            Service service = Service.create(url, qname);
            return service.getPort(LoginService.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 内部类用于模拟存储学生信息，实际应用中应替换为数据库实体类
    private static class StudentInfo {
        private final String studentName;
        private final String studentGender;
        private final String studentId;
        private final String birthDate;
        private final String department;
        private final String grade;
        private final String studentEmail;

        public StudentInfo(String studentName, String studentGender, String studentId, String birthDate, String department, String grade, String studentEmail) {
            this.studentName = studentName;
            this.studentGender = studentGender;
            this.studentId = studentId;
            this.birthDate = birthDate;
            this.department = department;
            this.grade = grade;
            this.studentEmail = studentEmail;
        }

        public String getStudentName() {
            return studentName;
        }

        public String getStudentGender() {
            return studentGender;
        }

        public String getStudentId() {
            return studentId;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public String getDepartment() {
            return department;
        }

        public String getGrade() {
            return grade;
        }
        public String getStudentEmail() {
            return studentEmail;
        }
    }
}