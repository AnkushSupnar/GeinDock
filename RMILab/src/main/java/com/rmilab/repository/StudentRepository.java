package com.rmilab.repository;

import com.rmilab.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("select s from Student s where s.email = ?1")
    Optional<Student> findByEmail(String email);

    @Query("select s from Student s where s.email = ?1 and s.otp = ?2")
    Optional<Student> findByEmailAndOtp(String email, String otp);



}
