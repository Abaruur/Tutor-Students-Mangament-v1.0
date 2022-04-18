package com.teksystems.service;

import com.teksystems.model.Tutor;
import com.teksystems.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    @Autowired
    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public void save(Tutor tutor) {
        tutorRepository.save(tutor);
    }

    public List<Tutor> list() {
        return tutorRepository.findAll();
    }

    public void delete(Integer tutorId) {
        Optional<Tutor> tutor = tutorRepository.findById(tutorId);
        tutor.ifPresent(tutorRepository::delete);
    }

    public Optional<Tutor> getTutorById(Integer tutorId) {
        return tutorRepository.findById(tutorId);
    }

}
