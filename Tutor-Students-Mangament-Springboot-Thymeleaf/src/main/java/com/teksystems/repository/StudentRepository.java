package com.teksystems.repository;

import com.teksystems.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findStudentByUsernameAndPassword(String username, String password);
    Optional<Student> findStudentsByUsername(String username);
}
