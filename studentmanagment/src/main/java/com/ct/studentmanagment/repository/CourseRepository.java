package com.ct.studentmanagment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ct.studentmanagment.model.Courses;

public interface CourseRepository extends JpaRepository<Courses, Long> {

    boolean existsByCourseCodeIgnoreCase(String code);
}
