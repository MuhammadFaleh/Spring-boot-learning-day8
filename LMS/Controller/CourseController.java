package com.lab7.LMS.Controller;

import com.lab7.LMS.Api.ApiResponse;
import com.lab7.LMS.Model.Course;
import com.lab7.LMS.Model.Grade;
import com.lab7.LMS.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    //crud
    @GetMapping("/get-courses")
    public ResponseEntity<?> getCourses(){
        ArrayList<Course> courses = courseService.getCourses();
        if(courses.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no courses stored"));
        }
        return ResponseEntity.status(200).body(courses);
    }

    @PostMapping("/create-course")
    public ResponseEntity<?> createCourse(@RequestBody @Valid Course course, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if(courseService.createCourse(course)){
            return ResponseEntity.status(200).body(new ApiResponse("course created successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("course already exist"));
    }

    @PutMapping("/update-course/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable String id, @RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if(courseService.updateCourse(id, course)){
            return ResponseEntity.status(200).body(new ApiResponse("course updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("failed to update course no match found"));
    }

    @DeleteMapping("/delete-course/{id}")
    public ResponseEntity<?> deleteGrade(@PathVariable String id){
        if(courseService.deleteCourse(id)){
            return ResponseEntity.status(200).body(new ApiResponse("course deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("failed to delete course, no match"));
    }

    //extra

    @GetMapping("/get-course-category/{category}")
    public ResponseEntity<?> getCourseByCategory(@PathVariable String category){
        ArrayList<Course> matchedCourses = courseService.getCourseByCategory(category);

        if(matchedCourses.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no course matches the entered category"));
        }
        return ResponseEntity.status(200).body(matchedCourses);
    }
}
