package com.ct.studentmanagment.service;

import com.ct.studentmanagment.dto.CourseDTO;

public interface CourseService {
	
	CourseDTO createCourse(CourseDTO courseDTO);
	
	boolean existsByCourseCode(String code);

}
