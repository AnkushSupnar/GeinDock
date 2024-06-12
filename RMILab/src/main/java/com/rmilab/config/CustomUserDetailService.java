package com.rmilab.config;

import com.rmilab.entity.LoginUser;
import com.rmilab.repository.LoginUserRepository;
import com.rmilab.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private LoginUserRepository loginUserRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomUserDetails customUserDetails = null;
        System.out.println("got email to check in ==="+email);
       // email="ankushsupnar@gmail.com";
        LoginUser loginUser = loginUserRepository.findByEmail(email);
        System.out.println("found user in custom = " + loginUser);
        if (loginUser == null) {
            System.out.println("Login User is null in CustomUserDetailService");
            throw new UsernameNotFoundException("User Not Found");
        } else {
            customUserDetails = new CustomUserDetails();
            customUserDetails.setLoginUser(loginUser);
            System.out.println("customerDetails="+ customUserDetails);
            return customUserDetails;
        }
    }
}
