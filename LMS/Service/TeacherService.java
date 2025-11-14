package com.lab7.LMS.Service;

import com.lab7.LMS.Model.Student;
import com.lab7.LMS.Model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@SuppressWarnings("LombokGetterMayBeUsed")
@Service
@RequiredArgsConstructor
public class TeacherService {
    ArrayList<Teacher> teachers = new ArrayList<>();
    private final CourseService courseService;

    public ArrayList<Teacher> getTeachers(){
        return teachers;
    }

    public int createTeachers(Teacher teacher){
        if(!teacherExist(teacher.getId())){
            if(courseService.courseExist(teacher.getManagedCourseID())){
                teachers.add(teacher);
                return 1;
            }else return 2;
        }

        return 0;
    }

    public int updateTeacher(String id, Teacher teacher){
        for (int i = 0; i < teachers.size(); i++) {
            if(teachers.get(i).getId().equalsIgnoreCase(id)){
                if(courseService.courseExist(teacher.getManagedCourseID())){
                    teachers.set(i, teacher);
                    return 1;
                }
                return 2;
            }
        }
        return 0;
    }

    public boolean deleteTeacher(String id){
        for (int i = 0; i < teachers.size() ; i++) {
            if(teachers.get(i).getId().equalsIgnoreCase(id)){
                teachers.remove(i);
                return true;
            }
        }
        return false;
    }

    //logic
    public boolean teacherExist(String id){
        if(teachers.stream().anyMatch(e-> e.getId().equalsIgnoreCase(id))){
            return true;
        }
        return false;
    }

    //extra
    public Teacher getTeacherByID(String id){
        for (Teacher teacher: teachers){
            if(teacher.getId().equalsIgnoreCase(id)){
                return teacher;
            }
        }
        return null;
    }

    public int updateTeacherCourse(String id, String courseID){
        if(!courseService.courseExist(courseID)){
            return 2;
        }
        for (int i = 0; i < teachers.size(); i++) {
            if(teachers.get(i).getId().equalsIgnoreCase(id)){
                teachers.get(i).setManagedCourseID(courseID);
                return 1;
            }
        }
        return 0;
    }
}
