package com.rmilab.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Getter
//@ToString
//@Builder
public class RegistrationDTO {
    private Long id;
    private String first_name;
    private String institute_name;
    private String institute_department;
    private String last_name ;
    private String email;
    private String country_code;
    private String mobile;
    private String institute;
    private String purpose;
    private String department;
    private String country;
    private String state;
    private String city;
    private String otp;
    private Boolean verified;
    private String verification_method;
    private Boolean is_institute;
    private Boolean is_student;
    private String select_institute;
	public RegistrationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RegistrationDTO(Long id, String first_name, String institute_name, String institute_department,
			String last_name, String email, String country_code, String mobile, String institute, String purpose,
			String department, String country, String state, String city, String otp, Boolean verified,
			String verification_method, Boolean is_institute, Boolean is_student, String select_institute) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.institute_name = institute_name;
		this.institute_department = institute_department;
		this.last_name = last_name;
		this.email = email;
		this.country_code = country_code;
		this.mobile = mobile;
		this.institute = institute;
		this.purpose = purpose;
		this.department = department;
		this.country = country;
		this.state = state;
		this.city = city;
		this.otp = otp;
		this.verified = verified;
		this.verification_method = verification_method;
		this.is_institute = is_institute;
		this.is_student = is_student;
		this.select_institute = select_institute;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getInstitute_name() {
		return institute_name;
	}
	public void setInstitute_name(String institute_name) {
		this.institute_name = institute_name;
	}
	public String getInstitute_department() {
		return institute_department;
	}
	public void setInstitute_department(String institute_department) {
		this.institute_department = institute_department;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public Boolean getVerified() {
		return verified;
	}
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	public String getVerification_method() {
		return verification_method;
	}
	public void setVerification_method(String verification_method) {
		this.verification_method = verification_method;
	}
	public Boolean getIs_institute() {
		return is_institute;
	}
	public void setIs_institute(Boolean is_institute) {
		this.is_institute = is_institute;
	}
	public Boolean getIs_student() {
		return is_student;
	}
	public void setIs_student(Boolean is_student) {
		this.is_student = is_student;
	}
	public String getSelect_institute() {
		return select_institute;
	}
	public void setSelect_institute(String select_institute) {
		this.select_institute = select_institute;
	}
	@Override
	public String toString() {
		return "RegistrationDTO [id=" + id + ", first_name=" + first_name + ", institute_name=" + institute_name
				+ ", institute_department=" + institute_department + ", last_name=" + last_name + ", email=" + email
				+ ", country_code=" + country_code + ", mobile=" + mobile + ", institute=" + institute + ", purpose="
				+ purpose + ", department=" + department + ", country=" + country + ", state=" + state + ", city="
				+ city + ", otp=" + otp + ", verified=" + verified + ", verification_method=" + verification_method
				+ ", is_institute=" + is_institute + ", is_student=" + is_student + ", select_institute="
				+ select_institute + "]";
	}
    
    
}
