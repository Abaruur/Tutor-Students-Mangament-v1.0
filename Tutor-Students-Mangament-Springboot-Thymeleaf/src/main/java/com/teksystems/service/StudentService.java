package com.teksystems.service;

import com.teksystems.model.Class;
import com.teksystems.model.Student;
import com.teksystems.repository.ClassRepository;
import com.teksystems.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, ClassRepository classRepository) {
        this.studentRepository = studentRepository;
        this.classRepository = classRepository;
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public List<Student> list() {
        return studentRepository.findAll();
    }

    public void delete(Integer studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        student.ifPresent(studentRepository::delete);
    }

    public Optional<Student> getStudentById(Integer studentId) {
        return studentRepository.findById(studentId);
    }

    public List<Student> getAllStudentsByClassId(Integer classId) {
        Optional<Class> optionalClass = classRepository.findById(classId);
        HashMap<Object, Student> map = new HashMap<>();
        List<Student> studentList = studentRepository.findAll();
        for (Student std : studentList
        ) {
            for (Class cl : std.getStudentBookedClasses()
            ) {
                if (cl.equals(optionalClass.get())) {
                    map.put(std.getStudentId(), std);
                }
            }
        }
        System.out.println(map.size());
        List<Student> afterMapStudentList = new ArrayList<>();

        map.forEach((key, value) -> {
            Student student = new Student();
            student.setStudentId(value.getStudentId());
            student.setStudentName(value.getStudentName());
            student.setUsername(value.getUsername());
            student.setPassword(value.getPassword());
            afterMapStudentList.add(student);
        });
        return afterMapStudentList;
    }
}
