package com.lab7.LMS.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {
    @NotBlank(message = "course id should not be empty")
    @Pattern(regexp = "^[a-zA-Z]{2}\\d{3}$", message =
            "course id should be 5 characters 2 letters followed by 3 numbers like 'CS112'")
    private String id;
    @NotBlank(message = "title must not be empty")
    @Size(max = 100, message = "title must not be longer than 100 characters")
    private String title;
    @NotBlank(message = "description can not be empty")
    @Size(min = 10, max=150, message = "description must be between 10 and 150 characters")
    private String description;
    @NotBlank(message = "category can not be empty")
    @Pattern(regexp = "^(?i)(bootcamp|seminar|workshop|course)$",
            message = "category must be 'bootcamp' 'seminar' 'workshop' 'course'")
    private String category;
}
