package com.rmilab.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Student")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private String firstName;
    private String lastName ;
    @Column(unique = true)
    private String email;
    private String countryCode;
    private String mobile;
    @ManyToOne
    @JoinColumn(name="instituteId")
    private Institute institute;
    private String purpose;
    private String department;
    private String country;
    private String state;
    private String city;
    private String otp;
    private Boolean verified;
    private String verificationMethod;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(Long studentId, String firstName, String lastName, String email, String countryCode, String mobile,
			Institute institute, String purpose, String department, String country, String state, String city,
			String otp, Boolean verified, String verificationMethod) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.countryCode = countryCode;
		this.mobile = mobile;
		this.institute = institute;
		this.purpose = purpose;
		this.department = department;
		this.country = country;
		this.state = state;
		this.city = city;
		this.otp = otp;
		this.verified = verified;
		this.verificationMethod = verificationMethod;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Institute getInstitute() {
		return institute;
	}
	public void setInstitute(Institute institute) {
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
	public String getVerificationMethod() {
		return verificationMethod;
	}
	public void setVerificationMethod(String verificationMethod) {
		this.verificationMethod = verificationMethod;
	}
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", countryCode=" + countryCode + ", mobile=" + mobile + ", institute=" + institute
				+ ", purpose=" + purpose + ", department=" + department + ", country=" + country + ", state=" + state
				+ ", city=" + city + ", otp=" + otp + ", verified=" + verified + ", verificationMethod="
				+ verificationMethod + "]";
	}

    
    
}
