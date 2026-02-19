package com.ct.studentmanagment.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ct.studentmanagment.dto.CourseDTO;
import com.ct.studentmanagment.model.Courses;
import com.ct.studentmanagment.repository.CourseRepository;
import com.ct.studentmanagment.service.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper mapper;

    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper mapper) {
        this.courseRepository = courseRepository;
        this.mapper = mapper;
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Courses courses = mapper.map(courseDTO, Courses.class);
        courseRepository.save(courses);
        return mapper.map(courses, CourseDTO.class);
    }

    @Override
    public boolean existsByCourseCode(String code) {
        // ✅ FIXED: correct method call
        return courseRepository.existsByCourseCodeIgnoreCase(code);
    }
}
