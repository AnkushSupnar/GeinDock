package com.rmilab.dto;

import lombok.*;

//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Getter
//@ToString
//@Builder
public class LoginUserDTO {
    Long user_id;
    String name;
    String entity_type;
	public LoginUserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginUserDTO(Long user_id, String name, String entity_type) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.entity_type = entity_type;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEntity_type() {
		return entity_type;
	}
	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}
	@Override
	public String toString() {
		return "LoginUserDTO [user_id=" + user_id + ", name=" + name + ", entity_type=" + entity_type + "]";
	}
    
    

}
