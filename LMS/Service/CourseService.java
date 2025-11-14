package com.lab7.LMS.Service;

import com.lab7.LMS.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {

    ArrayList<Course> courses = new ArrayList<>();


    //crud
    public ArrayList<Course> getCourses(){
        return courses;
    }

    public boolean createCourse(Course course){
        if(courseExist(course.getId())){
            return false;
        }
        courses.add(course);
        return true;
    }

    public boolean updateCourse(String id, Course course){
        if(!courseExist(id) && !course.getId().equalsIgnoreCase(id)){
            return false;
        }
        for (int i = 0; i < courses.size() ; i++) {
            if(courses.get(i).getId().equalsIgnoreCase(id)){
                courses.set(i, course);
                return true;
            }
        }

        return false;
    }

    public boolean deleteCourse(String id){
        for (int i = 0; i < courses.size() ; i++) {
            if(courses.get(i).getId().equalsIgnoreCase(id)){
                courses.remove(i);
                return true;
            }
        }
        return false;
    }
    //logic
    public boolean courseExist(String id){
        if(courses.stream().anyMatch(e-> e.getId().equalsIgnoreCase(id))){
            return true;
        }
        return false;
    }

    //extra

    public ArrayList<Course> getCourseByCategory(String category){
        ArrayList<Course> matchedCourses = new ArrayList<>();
        for (int i = 0; i < courses.size() ; i++) {
            if(courses.get(i).getCategory().equalsIgnoreCase(category)){
                matchedCourses.add(courses.get(i));
            }
        }
        return matchedCourses;
    }

}
