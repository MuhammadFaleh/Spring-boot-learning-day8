package com.lab7.LMS.Controller;



import com.lab7.LMS.Api.ApiResponse;
import com.lab7.LMS.Model.Student;
import com.lab7.LMS.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    //crud
    @GetMapping("/get-students")
    public ResponseEntity<?> getStudents(){
        ArrayList<Student> students = studentService.getStudents();
        if(students.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no students entered"));
        }
        return ResponseEntity.status(200).body(students);
    }

    @PostMapping("/create-students")
    public ResponseEntity<?> createStudents(@RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        int status = studentService.createStudents(student);
        if( status == 1){
            return ResponseEntity.status(200).body(new ApiResponse("student created successfully"));
        } else if(status == 2){
            return ResponseEntity.status(400).body(new ApiResponse("course was not found"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("student already exist"));

    }

    @PutMapping("/update-student/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String id, @RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        int status = studentService.updateStudent(id, student);
        if (status == 1) {
            return ResponseEntity.status(200).body(new ApiResponse("student updated successfully"));
        } else if (status == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("course was not found"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("student id was not found"));
    }

    @DeleteMapping("/delete-student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id){
        if(studentService.deleteStudent(id)){
            return ResponseEntity.status(200).body(new ApiResponse("student deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("failed to delete student"));
    }


    @GetMapping("/search-id/{id}")
    public ResponseEntity<?> getStudentsByID(@PathVariable String id){
        Student student = studentService.getStudentByID(id);

        if(student == null){
            return ResponseEntity.status(400).body(new ApiResponse("no student match that id"));
        }
        return ResponseEntity.status(200).body(student);
    }

    @GetMapping("/search-course/{courseID}")
    public ResponseEntity<?> getStudentsByCourse(@PathVariable String courseID){
        ArrayList<Student> student = studentService.getStudentsByCourse(courseID);

        if(student.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no student enrolled in that course"));
        }
        return ResponseEntity.status(200).body(student);
    }

    @PutMapping("update-student-course/{studentID}/{courseID}")
    public ResponseEntity<?> updateStudentCourse(@PathVariable String studentID, @PathVariable String courseID){
        int status = studentService.updateStudentCourse(studentID, courseID);
        if (status == 1) {
            return ResponseEntity.status(200).body(new ApiResponse("student course updated successfully"));
        } else if (status == 2) {
            return ResponseEntity.status(400).body(new ApiResponse("course was not found"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("student id was not found"));
    }
}
