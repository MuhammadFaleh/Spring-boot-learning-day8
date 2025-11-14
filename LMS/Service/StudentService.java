package com.lab7.LMS.Service;

import com.lab7.LMS.Model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@SuppressWarnings("LombokGetterMayBeUsed")
@Service
@RequiredArgsConstructor
public class StudentService {
    ArrayList<Student> students = new ArrayList<>();
    private final CourseService courseService;

    public ArrayList<Student> getStudents(){
        return students;
    }

    public int createStudents(Student student){
        if(!studentExist(student.getId())){
            if(courseService.courseExist(student.getEnrolledCourseID())){
                students.add(student);
                return 1;
            }else return 2;
        }

        return 0;
    }

    public int updateStudent(String id, Student student){
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getId().equalsIgnoreCase(id)){
                if(courseService.courseExist(student.getEnrolledCourseID())){
                    students.set(i, student);
                    return 1;
                }
                return 2;
            }
        }
        return 0;
    }

    public boolean deleteStudent(String id){
        for (int i = 0; i < students.size() ; i++) {
            if(students.get(i).getId().equalsIgnoreCase(id)){
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    //logic
    public boolean studentExist(String id){
        if(students.stream().anyMatch(e-> e.getId().equalsIgnoreCase(id))){
            return true;
        }
        return false;
    }

    //extra
    public Student getStudentByID(String id){
        for (Student student : students){
            if(student.getId().equalsIgnoreCase(id)){
                return student;
            }
        }
        return null;
    }

    public int updateStudentCourse(String id, String courseID){
        if(!courseService.courseExist(courseID)){
            return 2;
        }
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getId().equalsIgnoreCase(id)){
                students.get(i).setEnrolledCourseID(courseID);
                return 1;
            }
        }
        return 0;
    }

    public ArrayList<Student> getStudentsByCourse(String courseID){
        ArrayList<Student> matchedStudents = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getEnrolledCourseID().equalsIgnoreCase(courseID)){
                matchedStudents.add(students.get(i));
            }
        }
        return matchedStudents;
    }
}
