package com.lab7.LMS.Controller;


import com.lab7.LMS.Api.ApiResponse;
import com.lab7.LMS.Model.Teacher;
import com.lab7.LMS.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    //crud
    @GetMapping("/get-teachers")
    public ResponseEntity<?> getTeachers(){
        ArrayList<Teacher> teachers = teacherService.getTeachers();

        if(teachers.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no teachers have been created"));
        }
        return ResponseEntity.status(200).body(teachers);
    }

    @PostMapping("/create-teachers")
    public ResponseEntity<?> createTeachers(@RequestBody @Valid Teacher teacher, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        int status = teacherService.createTeachers(teacher);
        if( status == 1){
            return ResponseEntity.status(200).body(new ApiResponse("teacher created successfully"));
        } else if(status == 2){
            return ResponseEntity.status(400).body(new ApiResponse("course was not found"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("teacher already exist"));

    }

    @PutMapping("/update-teacher/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable String id, @RequestBody @Valid Teacher teacher, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        int status = teacherService.updateTeacher(id, teacher);
        if (status == 1) {
            return ResponseEntity.status(200).body(new ApiResponse("teacher updated successfully"));
        } else if (status == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("course was not found"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("teacher id was not found"));
    }

    @DeleteMapping("/delete-teacher/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable String id){
        if(teacherService.deleteTeacher(id)){
            return ResponseEntity.status(200).body(new ApiResponse("teacher deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("failed to delete teacher"));
    }


    //extra
    @GetMapping("/search-id/{id}")
    public ResponseEntity<?> getTeachersByID(@PathVariable String id){
        Teacher teacher = teacherService.getTeacherByID(id);

        if(teacher == null){
            return ResponseEntity.status(400).body(new ApiResponse("no teacher have that id"));
        }
        return ResponseEntity.status(200).body(teacher);
    }

    @PutMapping("update-teacher-course/{teacherID}/{courseID}")
    public ResponseEntity<?> updateStudentCourse(@PathVariable String teacherID, @PathVariable String courseID){
        int status = teacherService.updateTeacherCourse(teacherID, courseID);
        if (status == 1) {
            return ResponseEntity.status(200).body(new ApiResponse("teacher course updated successfully"));
        } else if (status == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("course was not found"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("teacher id was not found"));
    }

}

