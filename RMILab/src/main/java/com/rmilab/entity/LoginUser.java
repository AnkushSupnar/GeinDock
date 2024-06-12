package com.rmilab.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import com.rmilab.entity.*;

@Entity
@Table(name="loginUser")
//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LoginUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long loginId;
    @Column(name="email")
    String email;
    String userType;
    Long userTypeId;
    String password;
    Boolean isActive;
    String otp;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="loginUserRole",joinColumns = @JoinColumn(name="loginId"),inverseJoinColumns = @JoinColumn(name="roleId"))
    Set<Role> roles;
	public LoginUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginUser(Long loginId, String email, String userType, Long userTypeId, String password, Boolean isActive,
			String otp, Set<Role> roles) {
		super();
		this.loginId = loginId;
		this.email = email;
		this.userType = userType;
		this.userTypeId = userTypeId;
		this.password = password;
		this.isActive = isActive;
		this.otp = otp;
		this.roles = roles;
	}
	public Long getLoginId() {
		return loginId;
	}
	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Long getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

    
    
}
