package com.teksystems.service;

import com.teksystems.model.Lesson;
import com.teksystems.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public void save(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public List<Lesson> list() {
        return lessonRepository.findAll();
    }

    public void delete(Integer lessonId) {
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);
        lesson.ifPresent(lessonRepository::delete);
    }

}
