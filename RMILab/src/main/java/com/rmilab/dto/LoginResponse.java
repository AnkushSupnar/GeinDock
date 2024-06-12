package com.rmilab.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginResponse {
    String message;
    boolean success;
}
