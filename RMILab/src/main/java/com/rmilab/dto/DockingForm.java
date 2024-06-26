package com.rmilab.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DockingForm {
    private MultipartFile protein;
    private MultipartFile ligand;
    private int cavities;
    private String email;
}
