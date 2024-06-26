package com.rmilab.service;
import com.rmilab.entity.Student;
import com.rmilab.repository.StudentRepository;
import com.rmilab.util.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SendEmail sendEmail;
    public String saveStudent(Student student){
        if(student.getVerificationMethod()==null){
            student.setVerificationMethod("email");
        }
        if(student.getVerified()==null){
            student.setVerified(false);
        }
        String otpResult =  sendEmail.sendEmail(student.getEmail(),student.getFirstName()+" "+student.getLastName());
        if(otpResult.equalsIgnoreCase("Failed")){
            return "fail:OTP Send Failed";
        }
        else {
            student.setOtp(otpResult);
            studentRepository.save(student);
            return "success:User Save Success";
        }
    }
    public Boolean checkEmailExist(String email){
        return studentRepository.findByEmail(email).orElse(null)!=null;
    }
    public Student getByEmail(String email){
        return studentRepository.findByEmail(email).orElse(null);
    }
    public Student findByEmailAndOtp(String email,String otp){
        return studentRepository.findByEmailAndOtp(email,otp).orElse(null);
    }
}
