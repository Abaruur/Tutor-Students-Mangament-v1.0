package com.teksystems.controller;

import com.teksystems.model.LoginDTO;
import com.teksystems.model.Student;
import com.teksystems.model.Tutor;
import com.teksystems.repository.StudentRepository;
import com.teksystems.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthenticationController {

    TutorRepository tutorRepository;
    StudentRepository studentRepository;

    @Autowired
    public AuthenticationController(TutorRepository tutorRepository, StudentRepository studentRepository) {
        this.tutorRepository = tutorRepository;
        this.studentRepository = studentRepository;
    }

    @PostMapping("/login")
    public String login(@Valid LoginDTO loginDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("loginError", true);
            return "login";
        } else {
            Optional<Student> student = studentRepository.findStudentByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
            Optional<Tutor> tutor = tutorRepository.findTutorByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
            if (student.isPresent()) {
                model.addAttribute("studentId", student.get().getStudentId());
                return "redirect:/student/" + student.get().getStudentId();
            } else if (tutor.isPresent()) {
                model.addAttribute("tutorId", tutor.get().getTutorId());
                return "redirect:/tutor/" + tutor.get().getTutorId();
            } else {
                model.addAttribute("loginError", true);
                return "login";
            }
        }
    }

    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "login";
    }

    @GetMapping("/register/tutor/form")
    public String tutorRegistration(Model model) {
        model.addAttribute("tutor", new Tutor());
        return "tutor-registration";
    }


    @PostMapping("/register/tutor/form-submission")
    public String tutorRegistrationSubmission(@Valid Tutor tutor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "tutor-registration";
        } else {
            if (tutorRepository.findTutorByUsername(tutor.getUsername()).isPresent()) {
                model.addAttribute("userExisting", true);
                return "tutor-registration";
            } else {
                tutorRepository.save(tutor);
                return "redirect:/";
            }
        }
    }

    @GetMapping("/register/student/form")
    public String studentRegistration(Model model) {
        model.addAttribute("student", new Student());
        return "student-registration";
    }


    @PostMapping("/register/student/form-submission")
    public String studentRegistrationSubmission(@Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "student-registration";
        } else {
            if (studentRepository.findStudentsByUsername(student.getUsername()).isPresent()) {
                model.addAttribute("userExisting", true);
                return "student-registration";
            } else {
                studentRepository.save(student);
                return "redirect:/";
            }
        }
    }
}
