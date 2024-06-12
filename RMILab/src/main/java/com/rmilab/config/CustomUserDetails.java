package com.rmilab.config;

import com.rmilab.entity.LoginUser;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
   private LoginUser loginUser;

   public Collection<? extends GrantedAuthority> getAuthorities() {
      System.out.println("Setting roles to custom user");
      return (Collection)this.loginUser.getRoles().stream().map((role) -> {
         return new SimpleGrantedAuthority("ROLE_" + role.getRole());
      }).collect(Collectors.toList());
   }

   public String getPassword() {
      return this.loginUser.getPassword();
   }

   public String getUsername() {
      return this.loginUser.getEmail();
   }

   public boolean isAccountNonExpired() {
      return true;
   }

   public boolean isAccountNonLocked() {
      return true;
   }

   public boolean isCredentialsNonExpired() {
      return true;
   }

   public boolean isEnabled() {
      return true;
   }

  /* public static com.rmilab.config.CustomUserDetails.CustomUserDetailsBuilder builder() {
      return new com.rmilab.config.CustomUserDetails.CustomUserDetailsBuilder();
   }*/

   public LoginUser getLoginUser() {
      return this.loginUser;
   }

   public void setLoginUser(final LoginUser loginUser) {
      this.loginUser = loginUser;
   }

   public CustomUserDetails(final LoginUser loginUser) {
      this.loginUser = loginUser;
   }

   public CustomUserDetails() {
   }

   public String toString() {
      return "CustomUserDetails(loginUser=" + this.getLoginUser() + ")";
   }
}
    