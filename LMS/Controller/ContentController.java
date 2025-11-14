package com.lab7.LMS.Controller;

import com.lab7.LMS.Api.ApiResponse;
import com.lab7.LMS.Model.Content;
import com.lab7.LMS.Service.ContentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/content")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;

    //crud
    //crud
    @GetMapping("/get-content")
    public ResponseEntity<?> getContent(){
        ArrayList<Content> contents = contentService.getContents();
        if(contents.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no content stored"));
        }
        return ResponseEntity.status(200).body(contents);
    }

    @PostMapping("/create-content")
    public ResponseEntity<?> createContent(@RequestBody @Valid Content content, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if(contentService.createContent(content)){
            return ResponseEntity.status(200).body(new ApiResponse("content created successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("course doesn't exist"));
    }

    @PutMapping("/update-content/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable String id, @RequestBody @Valid Content content, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if(contentService.updateContent(id, content)){
            return ResponseEntity.status(200).body(new ApiResponse("content updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("failed to update content no match found"));
    }

    @DeleteMapping("/delete-content/{id}")
    public ResponseEntity<?> deleteGrade(@PathVariable String id){
        if(contentService.deleteContent(id)){
            return ResponseEntity.status(200).body(new ApiResponse("content deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("failed to delete content, no match"));
    }

    //extra

    @GetMapping("/get-content-courseID/{courseID}")
    public ResponseEntity<?> getContentByCourseID(@PathVariable String courseID){
        ArrayList<Content> matchedContents = contentService.getContentByCourseID(courseID);

        if(matchedContents.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no content matches the entered course id"));
        }
        return ResponseEntity.status(200).body(matchedContents);
    }

    @GetMapping("/get-content-length/{courseID}")
    public ResponseEntity<?> getContentLength(@PathVariable String courseID){
        int length = contentService.getContentLengthForCourse(courseID);
        if(length == 0){
            return ResponseEntity.status(400).body(new ApiResponse("no content matches the course entered"));
        }
        return ResponseEntity.status(200).body(new ApiResponse(String.format("the length of the course: %s is %d",
                courseID,length)));
    }
}
