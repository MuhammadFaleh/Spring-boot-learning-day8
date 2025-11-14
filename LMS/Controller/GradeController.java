package com.lab7.LMS.Controller;

import com.lab7.LMS.Api.ApiResponse;
import com.lab7.LMS.Model.Grade;
import com.lab7.LMS.Service.GradeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
// this was made into a separate controller and service for future expandability if students will have many courses
@RestController
@RequestMapping("/api/v1/grade")
@RequiredArgsConstructor
public class GradeController {
    private final GradeService gradeService;

    //crud
    @GetMapping("/get-grades")
    public ResponseEntity<?> getGrades(){
        ArrayList<Grade> grades = gradeService.getGrades();
        if(grades.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no grades stored"));
        }
        return ResponseEntity.status(200).body(grades);
    }

    @PostMapping("/create-grade")
    public ResponseEntity<?> createGrade(@RequestBody @Valid Grade grade, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        int status = gradeService.createGrade(grade);
        if(status == 1){
            return ResponseEntity.status(200).body(new ApiResponse("grade created successfully"));
        }
        if(status == 2){
            return ResponseEntity.status(400).body(new ApiResponse("failed to create grade no course matches the id"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("failed to create grade no student matches the id"));
    }

    @PutMapping("/update-grade/{studentID}")
    public ResponseEntity<?> updateGrade(@PathVariable String studentID, @RequestBody @Valid Grade grade, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        int status = gradeService.updateGrade(studentID, grade);
        if(status == 1){
            return ResponseEntity.status(200).body(new ApiResponse("grade updated successfully"));
        }
        if(status == 2){
            return ResponseEntity.status(400).body(new ApiResponse("failed to update grade no course matches the id"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("failed to update grade no student matches the id"));
    }

    @DeleteMapping("/delete-grade/{studentID}")
    public ResponseEntity<?> deleteGrade(@PathVariable String studentID){
        if(gradeService.deleteGrade(studentID)){
            return ResponseEntity.status(200).body(new ApiResponse("grade deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("failed to delete grade no student matches the id"));
    }

    //extra

    @GetMapping("/get-student-grade/{studentID}")
    public ResponseEntity<?> getGradeByStudentID(@PathVariable String studentID){
        Grade grade = gradeService.getGradeByStudentID(studentID);
        if(grade == null){
            return ResponseEntity.status(400).body(new ApiResponse("no grade matches student id"));
        }
        return ResponseEntity.status(200).body(grade);
    }

    @GetMapping("/get-avg-grade/{courseID}")
    public ResponseEntity<?> getGradeAvgForCourse(@PathVariable String courseID){
        double avg = gradeService.getGradeAvgByCourse(courseID);
        if(avg == 0){
            return ResponseEntity.status(400).body(new ApiResponse("no grade matches course id"));
        }
        return ResponseEntity.status(200).body(new ApiResponse(String.format("average for course:%s is: %.1f",
                courseID,avg)));
    }
}
