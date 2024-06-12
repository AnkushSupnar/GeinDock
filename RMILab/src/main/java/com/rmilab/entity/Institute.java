package com.rmilab.entity;

import javax.persistence.*;

@Entity
@Table(name = "Institute")
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Builder
public class Institute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long instituteId;
    @Column(unique = true)
    String instituteName;
    String instituteDepartment;
    @Column(unique = true)
    String instituteEmail;
    String instituteCode;
    String instituteMobile;
    String instituteCountry;
    String instituteState;
    String instituteCity;
    private Boolean verified;
    private String verificationMethod;
    private String otp;
	public Institute() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Institute(Long instituteId, String instituteName, String instituteDepartment, String instituteEmail,
			String instituteCode, String instituteMobile, String instituteCountry, String instituteState,
			String instituteCity, Boolean verified, String verificationMethod, String otp) {
		super();
		this.instituteId = instituteId;
		this.instituteName = instituteName;
		this.instituteDepartment = instituteDepartment;
		this.instituteEmail = instituteEmail;
		this.instituteCode = instituteCode;
		this.instituteMobile = instituteMobile;
		this.instituteCountry = instituteCountry;
		this.instituteState = instituteState;
		this.instituteCity = instituteCity;
		this.verified = verified;
		this.verificationMethod = verificationMethod;
		this.otp = otp;
	}
	public Long getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(Long instituteId) {
		this.instituteId = instituteId;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	public String getInstituteDepartment() {
		return instituteDepartment;
	}
	public void setInstituteDepartment(String instituteDepartment) {
		this.instituteDepartment = instituteDepartment;
	}
	public String getInstituteEmail() {
		return instituteEmail;
	}
	public void setInstituteEmail(String instituteEmail) {
		this.instituteEmail = instituteEmail;
	}
	public String getInstituteCode() {
		return instituteCode;
	}
	public void setInstituteCode(String instituteCode) {
		this.instituteCode = instituteCode;
	}
	public String getInstituteMobile() {
		return instituteMobile;
	}
	public void setInstituteMobile(String instituteMobile) {
		this.instituteMobile = instituteMobile;
	}
	public String getInstituteCountry() {
		return instituteCountry;
	}
	public void setInstituteCountry(String instituteCountry) {
		this.instituteCountry = instituteCountry;
	}
	public String getInstituteState() {
		return instituteState;
	}
	public void setInstituteState(String instituteState) {
		this.instituteState = instituteState;
	}
	public String getInstituteCity() {
		return instituteCity;
	}
	public void setInstituteCity(String instituteCity) {
		this.instituteCity = instituteCity;
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
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	@Override
	public String toString() {
		return "Institute [instituteId=" + instituteId + ", instituteName=" + instituteName + ", instituteDepartment="
				+ instituteDepartment + ", instituteEmail=" + instituteEmail + ", instituteCode=" + instituteCode
				+ ", instituteMobile=" + instituteMobile + ", instituteCountry=" + instituteCountry
				+ ", instituteState=" + instituteState + ", instituteCity=" + instituteCity + ", verified=" + verified
				+ ", verificationMethod=" + verificationMethod + ", otp=" + otp + "]";
	}
    
    
    
}
