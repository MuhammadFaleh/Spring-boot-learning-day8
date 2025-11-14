package com.lab7.LMS.Service;

import com.lab7.LMS.Model.Content;
import com.lab7.LMS.Model.Grade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@SuppressWarnings("LombokGetterMayBeUsed")
@Service
@RequiredArgsConstructor
public class ContentService {
    ArrayList<Content> contents = new ArrayList<>();
    private final CourseService courseService;

    //crud
    public ArrayList<Content> getContents(){
        return contents;
    }

    public boolean createContent(Content content){
        if(courseService.courseExist(content.getCourseID())){
            contents.add(content);
            return true;
        }
        return false;
    }

    public boolean updateContent(String id, Content content){
        if(!courseService.courseExist(content.getCourseID())){
            return false;
        }
        for (int i = 0; i < contents.size() ; i++) {
            if(contents.get(i).getId().equalsIgnoreCase(id)){
                contents.set(i, content);
                return true;
            }
        }

        return false;
    }

    public boolean deleteContent(String id){
        for (int i = 0; i < contents.size() ; i++) {
            if(contents.get(i).getId().equalsIgnoreCase(id)){
                contents.remove(i);
                return true;
            }
        }
        return false;
    }


    //extra
    public ArrayList<Content> getContentByCourseID(String courseID){
        ArrayList<Content> matchedContent = new ArrayList<>();
        for (int i = 0; i < contents.size() ; i++) {
            if(contents.get(i).getCourseID().equalsIgnoreCase(courseID)){
                matchedContent.add(contents.get(i));
            }
        }
        return matchedContent;
    }

    public int getContentLengthForCourse(String courseID){
        int count = 0;
        for (int i = 0; i < contents.size() ; i++) {
            if(contents.get(i).getCourseID().equalsIgnoreCase(courseID)){
                count++;
            }
        }
        return count;
    }
}
