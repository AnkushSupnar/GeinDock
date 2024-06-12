package com.rmilab.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginRequest {
    String email;
    String password;
}
