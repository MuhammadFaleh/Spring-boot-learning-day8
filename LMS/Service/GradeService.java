package com.lab7.LMS.Service;

import com.lab7.LMS.Model.Course;
import com.lab7.LMS.Model.Student;
import com.lab7.LMS.Service.CourseService;
import com.lab7.LMS.Model.Grade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@SuppressWarnings("LombokGetterMayBeUsed")
@Service
@RequiredArgsConstructor
public class GradeService {
    ArrayList<Grade> grades = new ArrayList<>();
    private final StudentService studentService;
    private final CourseService courseService;

    //crud
    public ArrayList<Grade> getGrades(){
        return grades;
    }

    public int createGrade(Grade grade){
        if(studentService.studentExist(grade.getStudentID())){
            if(courseService.courseExist(grade.getCourseID())){
            grades.add(grade);
            return 1;
            }
            return 2;
        }
        return 0;
    }

    public int updateGrade(String studentID, Grade grade){
        if(!courseService.courseExist(grade.getCourseID())){
            return 2;
        }
        for (int i = 0; i < grades.size() ; i++) {
            if(grades.get(i).getStudentID().equalsIgnoreCase(studentID)){
                grades.set(i, grade);
                return 1;
            }
        }

        return 0;
    }

    public boolean deleteGrade(String studentID){
        for (int i = 0; i < grades.size() ; i++) {
            if(grades.get(i).getStudentID().equalsIgnoreCase(studentID)){
                grades.remove(i);
                return true;
            }
        }
        return false;
    }


    //extra
    public Grade getGradeByStudentID(String studentID){
        for (int i = 0; i < grades.size() ; i++) {
            if(grades.get(i).getStudentID().equalsIgnoreCase(studentID)){
                return grades.get(i);
            }
        }
        return null;
    }

    public double getGradeAvgByCourse(String courseId){
        double avg = 0;
        int count = 0;
        for (int i = 0; i < grades.size() ; i++) {
            if(grades.get(i).getCourseID().equalsIgnoreCase(courseId)){
                avg += grades.get(i).getGrade();
                count++;
            }
        }
        if(count !=0) {
            return avg / count;
        }
        return 0;
    }
}
