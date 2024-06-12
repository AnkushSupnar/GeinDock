package com.rmilab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean
    public UserDetailsService getUserDetailService() {
        return new CustomUserDetailService();
    }

    @Bean
    public BCryptPasswordEncoder getPassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getUserDetailService());
        daoAuthenticationProvider.setPasswordEncoder(getPassword());
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
        // auth.userDetailsService(customUserDetailService)
        // .passwordEncoder(getPassword());
    }

    // Permit all for testing
    // @Override
    protected void configure1(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection
                .csrf().disable()
                // Disable session creation
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // Disable all security
                .authorizeRequests().anyRequest().permitAll()
                .and()
                // Disable form login security
                .formLogin().disable()
                // Disable HTTP Basic authentication
                .httpBasic().disable();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .csrf()
                .ignoringAntMatchers("/api/login", "/registerForm", "/registerForm", "/api/saveRegisterUser",
                        "api/verifyUser", "/api/verifyUser", "/util/**","/about","/contact")
                .and()
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .antMatchers("/home", "/pricing","/about","/contact").permitAll()
                .antMatchers("/api/login", "/docking").permitAll() // allow everyone to access the login endpoint
                .antMatchers("/registerForm", "/api/saveRegisterUser", "/api/saveRegisterUser", "api/verifyUser",
                        "/api/verifyUser", "/util/**","/about")
                .permitAll()
                .antMatchers(
                        "/docking_test",
                        "/dock/serverDockUpload",
                        "/stream-updates",
                        "/dockingVina",
                        "/dockingVinaResult")
                .hasRole("USER") // permit only users with USER role to access the /docking endpoint
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/home")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/home?error")
                .permitAll();

        /*
         * sucess login
         * http
         * .sessionManagement()
         * .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
         * .and()
         * .csrf()
         * .ignoringAntMatchers("/api/login","/registerForm","/registerForm",
         * "/api/saveRegisterUser","api/verifyUser","/api/verifyUser","/util/**")
         * .and()
         * .authorizeRequests()
         *
         *
         * .antMatchers("/css/**", "/js/**").permitAll()
         * .antMatchers("/home").permitAll()
         * .antMatchers("/api/login").permitAll() // allow everyone to access the login
         * endpoint
         * .antMatchers("/registerForm","/api/saveRegisterUser",
         * "/api/saveRegisterUser","api/verifyUser","/api/verifyUser","/util/**")
         * .permitAll()
         * .anyRequest().authenticated()
         * .and()
         * .formLogin()
         * .loginPage("/home")
         * .loginProcessingUrl("/login")
         * .defaultSuccessUrl("/docking")
         * .failureUrl("/home?error")
         * .permitAll();
         */
        /*
         * http
         * .csrf()
         * .ignoringAntMatchers("/api/login") // Exclude /api/login from CSRF protection
         * .and()
         * .authorizeRequests()
         *
         * .antMatchers("/css/**", "/js/**").permitAll() // Allow access to CSS and JS
         * files
         * .antMatchers("/home","/registerForm").permitAll() // Allow access to the home
         * page
         * .antMatchers("/api/login").authenticated() // Require authentication for the
         * login URL
         * .anyRequest().authenticated()
         * .and()
         * .formLogin()
         * .loginPage("/home")
         * .loginProcessingUrl("/api/login")
         * .defaultSuccessUrl("/docking/serverDockHome.jsp")
         * .permitAll();
         */

        /*
         * http
         * .authorizeRequests()
         * .antMatchers("/css/**", "/js/**").permitAll() // Allow access to CSS and JS
         * files
         * .antMatchers("/home").permitAll() // Allow access to the home page
         * .antMatchers("/api/login").authenticated() // Require authentication for the
         * login URL
         * .anyRequest().authenticated()
         * .and()
         * .formLogin()
         * .loginPage("/home")
         * .loginProcessingUrl("/api/login")
         * .defaultSuccessUrl("/docking")
         * .permitAll()
         * .and()
         * .logout()
         * .permitAll();
         */
    }
}