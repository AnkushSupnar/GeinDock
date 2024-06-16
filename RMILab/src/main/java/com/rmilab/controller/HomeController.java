package com.rmilab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        System.out.println("getting home page");
        return "home";
    }


    @GetMapping("/pricing")
    public String pricing() {
        System.out.println("getting pricing page");
        return "pricing";
    }

    @GetMapping("/about")
    public String about() {
        System.out.println("getting aboutus page");
        return "aboutus";
    }

    @GetMapping("/contact")
    public String contact() {
        System.out.println("getting contact page");
        return "contact";
    }

    @GetMapping("/about1")
    public String about1() {
        return "aboutus";
    }

    // Remove commented code or clarify with a TODO comment
}
