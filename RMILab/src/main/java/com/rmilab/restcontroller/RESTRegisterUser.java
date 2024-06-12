package com.rmilab.restcontroller;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rmilab.dto.DTOUtil;
import com.rmilab.dto.RegistrationDTO;
import com.rmilab.entity.Institute;
import com.rmilab.entity.LoginUser;
import com.rmilab.entity.Role;
import com.rmilab.entity.Student;
import com.rmilab.entity.User;
import com.rmilab.service.InstituteService;
import com.rmilab.service.LoginUserService;
import com.rmilab.service.StudentService;
import com.rmilab.service.UserService;
import com.rmilab.util.PasswordUtility;
@RestController
@RequestMapping("/api")
public class RESTRegisterUser {
    @Autowired
    UserService userService;
    @Autowired
    DTOUtil dtoUtil;
    @Autowired
    InstituteService instituteService;
    @Autowired
    PasswordUtility passwordUtility;
    @Autowired
    LoginUserService loginUserService;
    @Autowired
    StudentService studentService;


    @PostMapping("/saveRegisterUser")
    public ResponseEntity<String> saveRegisterUser(RegistrationDTO userDTO){
        System.out.println("save api="+userDTO);
        String result="Failed:Server Error";
        if(userDTO.getIs_institute()){
            Institute institute =  dtoUtil.getInstitute(userDTO);
            if(institute!=null){
                result = instituteService.saveInstitute(institute);
            }
            else{

                result="failed: Institute Not Created";
            }
        }
        else if(userDTO.getIs_student()){
            Student student = dtoUtil.getStudent(userDTO);
            if(student!=null){
                result = studentService.saveStudent(student);
            }
            else{
                result = "failed: Student Not Created";
            }
        }
        else {
            User user =  dtoUtil.getUser(userDTO);
            if(user!=null){
                result = userService.saveUser(user);
            }
            else{
                result = "failed: User Not Created";
            }
        }
        System.out.println("register save result="+result);
        if(result.contains("success"))
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        else
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }
    @PostMapping("/verifyUser")
    public ResponseEntity<String> verifyUser(@RequestParam String email,@RequestParam String otp,@RequestParam String password){
        System.out.println("in rest otp="+otp);
        System.out.println("in rest email="+email);
        String result="failed:Not Found";

        if(userService.checkEmailIdExist(email)){
            Set<Role> roleSet  = new HashSet<>();
            roleSet.add(new Role("USER"));
            User user = userService.verifyUser(email,otp);
            LoginUser loginUser =new LoginUser();
            loginUser.setUserType("user");
            loginUser.setUserTypeId(user.getUserId());
            loginUser.setOtp(user.getOtp());
            loginUser.setEmail(user.getEmail());
            loginUser.setRoles(roleSet);
            loginUser.setIsActive(true);
            loginUser.setPassword(passwordUtility.encodePassword(password));
//                LoginUser loginUser = LoginUser. builder()
//                        .userType("user")
//                        .userTypeId(user.getUserId())
//                        .otp(user.getOtp())
//                        .email(user.getEmail())
//                        .roles(roleSet)
//                        .isActive(true)
//                        .password(passwordUtility.encodePassword(password))
//                        .build();
                result =  loginUserService.saveLoginUser(loginUser);
        }else if(instituteService.findByInstituteEmail(email)){
            Institute institute = instituteService.verifyInstitute(email,otp);
            Set<Role> roleSet  = new HashSet<>();
            roleSet.add(new Role("INSTITUTE"));
            LoginUser loginUser =new LoginUser();
            loginUser.setUserType("institute");
            loginUser.setPassword(passwordUtility.encodePassword(password));
            loginUser.setIsActive(true);
            loginUser.setRoles(roleSet);
            loginUser.setEmail(institute.getInstituteEmail());
            loginUser.setOtp(institute.getOtp());
            loginUser.setUserTypeId(institute.getInstituteId());
            
//            LoginUser loginUser = LoginUser.builder()
//                    .userType("institute")
//                    .password(passwordUtility.encodePassword(password))
//                    .isActive(true)
//                    .roles(roleSet)
//                    .email(institute.getInstituteEmail())
//                    .otp(institute.getOtp())
//                    .userTypeId(institute.getInstituteId())
//                    .build();
            result =  loginUserService.saveLoginUser(loginUser);
        }
        else if(studentService.checkEmailExist(email)){
            Student student = studentService.findByEmailAndOtp(email,otp);
            Set<Role> roleSet  = new HashSet<>();
            roleSet.add(new Role("STUDENT"));
            
            LoginUser loginUser = new LoginUser();
            loginUser.setUserType("student");
            loginUser.setPassword(passwordUtility.encodePassword(password));
            loginUser.setIsActive(true);
            loginUser.setRoles(roleSet);
            loginUser.setEmail(student.getEmail());
            loginUser.setOtp(student.getOtp());
            loginUser.setUserTypeId(student.getStudentId());

            
            
//            LoginUser loginUser = LoginUser.builder()
//                    .userType("student")
//                    .password(passwordUtility.encodePassword(password))
//                    .isActive(true)
//                    .roles(roleSet)
//                    .email(student.getEmail())
//                    .otp(student.getOtp())
//                    .userTypeId(student.getStudentId())
//                    .build();
            result =  loginUserService.saveLoginUser(loginUser);
        }
        else{
            result = "failed:OPT Not Matched";
        }
    return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
