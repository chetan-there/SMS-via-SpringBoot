package com.ct.studentmanagment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ct.studentmanagment.dto.CourseDTO;
import com.ct.studentmanagment.service.CourseService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/course")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/new")
    public String showCreateCourse(Model model) {
        model.addAttribute("courseDto", new CourseDTO());
        return "add-course";
    }

    @GetMapping("/list")
    public String listCourses(Model model) {
        model.addAttribute("courseDTO", new CourseDTO());
        return "courses";
    }

    @PostMapping
    public String createCourse(@Valid @ModelAttribute("courseDto") CourseDTO courseDTO,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "add-course";
        }

        if (courseService.existsByCourseCode(courseDTO.getCourseCode())) {
            // ✅ FIXED error message format
            bindingResult.rejectValue("courseCode", "error.courseCode", "Code must be unique");
            return "add-course";
        }

        courseService.createCourse(courseDTO);

        redirectAttributes.addFlashAttribute("message", "Course created successfully");

        // ✅ FIXED redirect
        return "redirect:/course/list";
    }
}
