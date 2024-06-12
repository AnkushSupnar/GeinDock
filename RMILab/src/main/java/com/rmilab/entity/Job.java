package com.rmilab.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@ToString
//@Builder
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobName;
    private String proteinFile;
    private String ligandFiles ;
    private String outputFile;
    private String coordinates;
    private int cavities;
    private String email;
    private JobStatus status;
    private LocalDateTime submitDateTime;
    private String resultLink;
	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Job(Long id, String jobName, String proteinFile, String ligandFiles, String outputFile, String coordinates,
			int cavities, String email, JobStatus status, LocalDateTime submitDateTime, String resultLink) {
		super();
		this.id = id;
		this.jobName = jobName;
		this.proteinFile = proteinFile;
		this.ligandFiles = ligandFiles;
		this.outputFile = outputFile;
		this.coordinates = coordinates;
		this.cavities = cavities;
		this.email = email;
		this.status = status;
		this.submitDateTime = submitDateTime;
		this.resultLink = resultLink;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getProteinFile() {
		return proteinFile;
	}
	public void setProteinFile(String proteinFile) {
		this.proteinFile = proteinFile;
	}
	public String getLigandFiles() {
		return ligandFiles;
	}
	public void setLigandFiles(String ligandFiles) {
		this.ligandFiles = ligandFiles;
	}
	public String getOutputFile() {
		return outputFile;
	}
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
	public String getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	public int getCavities() {
		return cavities;
	}
	public void setCavities(int cavities) {
		this.cavities = cavities;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public JobStatus getStatus() {
		return status;
	}
	public void setStatus(JobStatus status) {
		this.status = status;
	}
	public LocalDateTime getSubmitDateTime() {
		return submitDateTime;
	}
	public void setSubmitDateTime(LocalDateTime submitDateTime) {
		this.submitDateTime = submitDateTime;
	}
	public String getResultLink() {
		return resultLink;
	}
	public void setResultLink(String resultLink) {
		this.resultLink = resultLink;
	}
	@Override
	public String toString() {
		return "Job [id=" + id + ", jobName=" + jobName + ", proteinFile=" + proteinFile + ", ligandFiles="
				+ ligandFiles + ", outputFile=" + outputFile + ", coordinates=" + coordinates + ", cavities=" + cavities
				+ ", email=" + email + ", status=" + status + ", submitDateTime=" + submitDateTime + ", resultLink="
				+ resultLink + "]";
	}


}
