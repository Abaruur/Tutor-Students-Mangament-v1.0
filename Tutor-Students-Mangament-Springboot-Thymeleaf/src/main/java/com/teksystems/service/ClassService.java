package com.teksystems.service;

import com.teksystems.model.Class;
import com.teksystems.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {
    private final ClassRepository classRepository;

    @Autowired
    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public void save(Class classObject) {
        classRepository.save(classObject);
    }

    public List<Class> list() {
        return classRepository.findAll();
    }

    public void delete(Integer classId) {
        Optional<Class> classOptional = classRepository.findById(classId);
        if (classOptional.isPresent()) {
            classRepository.delete(classOptional.get());
        }
    }

    public Optional<Class> getClassById(Integer classId) {
        return classRepository.findById(classId);
    }
}
