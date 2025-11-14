package com.lab7.LMS.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Student {
    @NotBlank(message = "student id should not be empty")
    @Size(min = 4, max = 4, message = "student id should be exactly 4")
    @Pattern(regexp = "^[0-9]*$", message = "student id should only be numbers")
    private String id;
    @NotBlank(message = "student email should not be empty")
    @Email(message = "enter a valid email")
    private String email;
    @NotBlank(message = "student name should not be empty")
    @Size(min=3, max = 40, message = "name should be between 3 and 40")
    @Pattern(regexp = "^[A-Za-z]*$", message = "student name should be only english letters")
    private String name;
    @NotNull(message = "age should not be empty")
    @Min(value = 10, message = "age should not younger than 10")
    private int age;
    @NotBlank(message = "course id should not be empty")
    @Pattern(regexp = "^[a-zA-Z]{2}\\d{3}$", message =
            "course id should be 5 characters 2 letters followed by 3 numbers like 'CS112'")
    private String enrolledCourseID; // students can create accounts without enrolling in any course

}
