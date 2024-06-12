package com.rmilab.restcontroller;

import com.rmilab.service.InstituteService;
import com.rmilab.service.StudentService;
import com.rmilab.service.UserService;
import com.rmilab.util.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/util")
public class UtilController {

    @Autowired
    private SendEmail sendEmail;
    @Autowired
    private UserService userService;
    @Autowired
    InstituteService instituteService;
    @Autowired
    StudentService studentService;
    @GetMapping("/sendEmail")
    public String sendEmail(@RequestParam String email,String userName)
    {
       return sendEmail.sendEmail(email,userName);
    }

    @GetMapping("/checkEmail")
    public String checkEmail(@RequestParam String email){
        System.out.println("in check email="+email);
        if(userService.checkEmailIdExist(email)){
            return "Exist";
        }
        else if(instituteService.findByInstituteEmail(email)){
            return "Exist";
        }
        else if(studentService.checkEmailExist(email)){
            return "Exist";
        }
        else{
            return "Not Exist";
        }
    }
}
