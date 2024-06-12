package com.rmilab.entity;

import lombok.*;

import javax.persistence.*;

@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@ToString
@Builder
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rollId", nullable = false)
    private Long roleId;

    String role;

    
    public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(Long roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}

	public Role(String role){
        this.role = role;
    }

//	public Role() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
}
