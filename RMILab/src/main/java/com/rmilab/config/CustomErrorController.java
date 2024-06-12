package com.rmilab.config;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorMessage = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        model.addAttribute("status", status);
        model.addAttribute("errorMessage", errorMessage);

        return "error"; // Replace with the name of your error page or view
    }

  //  @Override
    public String getErrorPath() {
        return PATH;
    }
}
