package com.rmilab.util;

import com.rmilab.entity.Institute;
import com.rmilab.entity.Student;
import com.rmilab.entity.User;
import com.rmilab.service.InstituteService;
import com.rmilab.service.StudentService;
import com.rmilab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class CommonUtility {
    @Autowired
    UserService userService;
    @Autowired
    InstituteService instituteService;
    @Autowired
    StudentService studentService;

    public User getUserByEmail(String email){
        return userService.getByEmail(email);
    }
    public Institute getInstituteByEmail(String email){
        return instituteService.getByEmail(email);
    }
    public Student getStudentByEmail(String email){
        return studentService.getByEmail(email);
    }

    public LocalDateTime getDateAndTime(String jobName){
        long timestamp = Long.parseLong(jobName);
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime;
    }
}
