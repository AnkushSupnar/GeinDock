package com.rmilab.service;

import com.rmilab.entity.LoginUser;
import com.rmilab.repository.LoginUserRepository;
import com.rmilab.util.PasswordUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginUserService {
    @Autowired
    LoginUserRepository repository;
    @Autowired
    PasswordUtility passwordUtility;
    public String saveLoginUser(LoginUser loginUser){
        repository.save(loginUser);
        return "success:Login User Save Success";
    }
    public String verifyLoginUser(String email,String password){
        LoginUser loginUser = repository.findByEmail(email);
        System.out.println("Login use in repo="+loginUser);
        if(loginUser==null){
            return "failed:Email ID Not Found";
        }
        else{
            if(passwordUtility.matches(password,loginUser.getPassword())){
                return "success:Login Success";
            }
            else{
                return "failed:Password Not Matched";
            }
        }
    }
    public LoginUser getByEmail(String email){
        LoginUser loginUser = repository.getByEmail(email);
        System.out.println("Login User in Service="+loginUser);
        return repository.getByEmail(email);
    }


}
