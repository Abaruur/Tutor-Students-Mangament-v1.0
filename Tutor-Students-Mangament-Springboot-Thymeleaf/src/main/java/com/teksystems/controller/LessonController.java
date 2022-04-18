package com.teksystems.controller;

import com.teksystems.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/list/{tutorId}")
    public String lessonList(@PathVariable(name = "tutorId") Integer tutorId, Model model) {
        model.addAttribute("tutorId", tutorId);
        model.addAttribute("lessonsList", lessonService.list());
        return "view-lessons";
    }
}
