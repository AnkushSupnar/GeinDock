package com.rmilab.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="EmailFormat")
public class EmailFormat {
	 @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 
	 private String formatName;
	 private String reciepientName;
	 private String subject;	 
	 private int otp;
	 private String body;
	public EmailFormat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmailFormat(int id, String formatName, String reciepientName, String subject, int otp, String body) {
		super();
		this.id = id;
		this.formatName = formatName;
		this.reciepientName = reciepientName;
		this.subject = subject;
		this.otp = otp;
		this.body = body;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFormatName() {
		return formatName;
	}
	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}
	public String getReciepientName() {
		return reciepientName;
	}
	public void setReciepientName(String reciepientName) {
		this.reciepientName = reciepientName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "EmailFormat [id=" + id + ", formatName=" + formatName + ", reciepientName=" + reciepientName
				+ ", subject=" + subject + ", otp=" + otp + ", body=" + body + "]";
	}
		 
	 
}
