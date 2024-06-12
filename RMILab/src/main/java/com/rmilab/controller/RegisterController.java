package com.rmilab.controller;


import com.rmilab.service.UserService;
import com.rmilab.util.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    @Autowired
    SendEmail sendEmail;
    @Autowired
    UserService service;
    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String register(Model model){
      //  model.addAttribute("user",new User());
        System.out.println("Register Method called");

        return "register";
       //return "registrationForm";
    }
    @RequestMapping(value="/registerForm", method= RequestMethod.GET)
    public String registerForm(Model model){
        //  model.addAttribute("user",new User());
        System.out.println("Register Method called");

        //return "register";
        return "registrationForm";
    }
//    @RequestMapping(value="/register", method= RequestMethod.POST)
//   // public String register(@RequestParam String username,@RequestParam String email){
//    public String register(@ModelAttribute("user") User user){
//        System.out.println(user);
//        service.saveRegisterUser(user);
//        return "register";
//    }

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam String email,@RequestParam String name){
    return "";
    }

}
