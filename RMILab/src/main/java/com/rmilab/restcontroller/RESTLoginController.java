package com.rmilab.restcontroller;

import com.rmilab.GeinDock;
import com.rmilab.config.CustomUserDetailService;
import com.rmilab.entity.Institute;
import com.rmilab.entity.LoginUser;
import com.rmilab.entity.Student;
import com.rmilab.entity.User;
import com.rmilab.service.*;
import com.rmilab.util.CommonUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RESTLoginController {

    @Autowired UserService userService;
    @Autowired InstituteService instituteService;
    @Autowired StudentService studentService;
    @Autowired CommonUtility commonUtility;
    @Autowired CustomUserDetailService customUserDetailService;
    @Autowired LoginUserService loginUserService;
    @Autowired FileService fileService;
    private static final Logger logger = LoggerFactory.getLogger(RESTLoginController.class);

    //@PostMapping("/login")
  //  @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login1(@RequestParam("username") String email, @RequestParam("password") String password,@RequestParam("_csrf")String  token) {

        System.out.println("got for login " + email + " " + password);

        String result = loginUserService.verifyLoginUser(email, password);
        System.out.println("result = " + result);

        if (result.contains("success")) {
            UserDetails custom = customUserDetailService.loadUserByUsername(email);
            System.out.println("custom User="+custom);
            Authentication authentication = new UsernamePasswordAuthenticationToken(custom, null, custom.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            LoginUser loginUser = loginUserService.getByEmail(email);
            Map<String, Object> userData = new HashMap<>();
            userData.put("email",email);
            userData.put("role", loginUser.getRoles());
            if(loginUser.getUserType().equalsIgnoreCase("user")) {
                userData.put("name", userService.getByEmail(email).getFirstName());
            }
            else if (loginUser.getUserType().equalsIgnoreCase("institute")) {
                userData.put("name",instituteService.getByEmail(email).getInstituteName());

            } else if (loginUser.getUserType().equalsIgnoreCase("student")) {
                userData.put("name",studentService.getByEmail(email).getFirstName());
            }
            else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
            //create user folder
            if(fileService.createUserDirectory(email).contains("Success")) {
                return ResponseEntity.ok(userData);
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown error occurred");
            }
           // return ResponseEntity.ok("Authentication successful");

        }else if(result.contains("failed")){
            System.out.println("returning failed "+result);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        }
        else if (result.equals("invalid")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown error occurred");
        }

    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestParam("username") String email,
                                   @RequestParam("password") String password,
                                   @RequestParam("_csrf") String token) {

        String result = loginUserService.verifyLoginUser(email, password);
        System.out.println("writing in log");
        logger.debug("login call");
        logger.info("login call info");
        logger.error("login call error");
        logger.warn("login call warn");
        System.out.println("writing in log done");
        Map<String, Object> response = new HashMap<>();
        if (result.contains("success")) {
            UserDetails custom = customUserDetailService.loadUserByUsername(email);
            Authentication authentication = new UsernamePasswordAuthenticationToken(custom, null, custom.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            LoginUser loginUser = loginUserService.getByEmail(email);

            Map<String, Object> userData = new HashMap<>();
            userData.put("email", email);
            userData.put("role", loginUser.getRoles());
            switch (loginUser.getUserType().toLowerCase()) {
                case "user":
                    userData.put("name", userService.getByEmail(email).getFirstName());
                    break;
                case "institute":
                    userData.put("name", instituteService.getByEmail(email).getInstituteName());
                    break;
                case "student":
                    userData.put("name", studentService.getByEmail(email).getFirstName());
                    break;
                default:
                    response.put("status", "error");
                    response.put("message", "Invalid credentials");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

            if (fileService.createUserDirectory(email).contains("Success")) {
                response.put("status", "success");
                response.put("message", "Login successful");
                response.put("data", userData);
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "error");
                response.put("message", "User directory creation failed");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } else if (result.equals("invalid")) {
            response.put("status", "error");
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }else if (result.contains("failed")) {
            String errorMessage = result.substring(result.indexOf(":") + 1);
            response.put("status", "error");
            response.put("message", errorMessage);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        else {
            response.put("status", "error");
            response.put("message", "Unknown error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}