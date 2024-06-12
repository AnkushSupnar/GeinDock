package com.rmilab.dto;

import com.rmilab.entity.Institute;
import com.rmilab.entity.Student;
import com.rmilab.entity.User;
import com.rmilab.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DTOUtil {
    @Autowired
    InstituteService instituteService;

    public User getUser(RegistrationDTO dto){
        if(dto.getIs_institute()) return null;
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setCity(dto.getCity());
        user.setCountry(dto.getCountry());
        user.setCountryCode(dto.getCountry_code());
        user.setState(dto.getState());
        user.setDepartment(dto.getDepartment());
        user.setFirstName(dto.getFirst_name());
        user.setMobile(dto.getMobile());
        user.setLastName(dto.getLast_name());
        user.setPurpose(dto.getPurpose());
        user.setInstitute(dto.getInstitute());
        user.setIsStudent(dto.getIs_student());
        user.setVerificationMethod(dto.getVerification_method());
        
        
//        return User.builder()
//                .email(dto.getEmail())
//                .city(dto.getCity())
//                .city(dto.getCity())
//                .country(dto.getCountry())
//                .countryCode(dto.getCountry_code())
//                .state(dto.getState())
//                .department(dto.getDepartment())
//                .firstName(dto.getFirst_name())
//                .mobile(dto.getMobile())
//                .lastName(dto.getLast_name())
//                .purpose(dto.getPurpose())
//                .institute(dto.getInstitute())
//                .isStudent(dto.getIs_student())
//                .verificationMethod(dto.getVerification_method()).build();
        return user;

    }
    public Institute getInstitute(RegistrationDTO dto){
        System.out.println("dto found="+dto);
        if(!dto.getIs_institute()) return null;
        Institute institute = new Institute();
        institute.setInstituteCity(dto.getCity());
        institute.setInstituteCode(dto.getCountry_code());
        institute.setInstituteEmail(dto.getEmail());
        institute.setInstituteCountry(dto.getCountry());
        institute.setInstituteMobile(dto.getMobile());
        institute.setInstituteName(dto.getInstitute_name());
        institute.setInstituteState(dto.getState());
        institute.setOtp(dto.getOtp());
        institute.setInstituteDepartment(dto.getInstitute_department());
//        return Institute.builder()
//                .instituteCity(dto.getCity())
//                .instituteCode(dto.getCountry_code())
//                .instituteEmail(dto.getEmail())
//                .instituteCountry(dto.getCountry())
//                .instituteMobile(dto.getMobile())
//                .instituteName(dto.getInstitute_name())
//                .instituteState(dto.getState())
//                .otp(dto.getOtp())
//                .instituteDepartment(dto.getInstitute_department())
//                .build();
        return institute;
    }
    public Student getStudent(RegistrationDTO dto){
        if(!dto.getIs_student())return null;
        Student student = new Student();
        student.setEmail(dto.getEmail());
        student.setVerified(dto.getVerified());
        student.setOtp(dto.getOtp());
        student.setCity(dto.getCity());
        student.setCountryCode(dto.getCountry_code());
        student.setLastName(dto.getLast_name());
        student.setMobile(dto.getMobile());
        student.setState(dto.getState());
        student.setPurpose(dto.getPurpose());
        student.setVerificationMethod(dto.getVerification_method());
        student.setInstitute(instituteService.findByInstitute_name(dto.getSelect_institute()));
        student.setDepartment(dto.getDepartment());
        student.setFirstName(dto.getFirst_name());
        student.setVerified(false);
        
//        return Student.builder()
//                .email(dto.getEmail())
//                .verified(dto.getVerified())
//                .otp(dto.getOtp())
//                .city(dto.getCity())
//                .countryCode(dto.getCountry_code())
//                .lastName(dto.getLast_name())
//                .mobile(dto.getMobile())
//                .state(dto.getState())
//                .purpose(dto.getPurpose())
//                .verificationMethod(dto.getVerification_method())
//                .institute(instituteService.findByInstitute_name(dto.getSelect_institute()))
//                .department(dto.getDepartment())
//                .firstName(dto.getFirst_name())
//                .verified(false)
//                .build();
        return student;
    }
    public LoginUserDTO getLoginDTOFromUser(User user){
    	
    	LoginUserDTO loginUserDto = new LoginUserDTO();
    	loginUserDto.setUser_id(user.getUserId());
    	loginUserDto.setEntity_type("user");
    	loginUserDto.setName(user.getFirstName()+" "+user.getLastName());
    	
//        return LoginUserDTO.builder()
//                .user_id(user.getUserId())
//                .entity_type("user")
//                .name(user.getFirstName()+" "+user.getLastName())
//                .build();
    	return loginUserDto;
    }
}
