package com.lab7.LMS.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Teacher {
    @NotBlank(message = "teacher id should not be empty")
    @Size(min = 4, max=4, message = "teacher id should be exactly 4 characters long")
    @Pattern(regexp = "^[0-9]*$", message = "teacher id should only be numbers")
    private String id;
    @NotBlank(message = "teacher email should not be empty")
    @Email(message = "enter a valid email")
    private String email;
    @NotBlank(message = "name should not be empty")
    @Size(min=3, max = 40, message = "name should be between 3 and 40")
    @Pattern(regexp = "^[A-Za-z]*$", message = "teacher name should be only english letters")
    private String name;
    @NotBlank(message = "course id should not be empty")
    @Pattern(regexp = "^[a-zA-Z]{2}\\d{3}$", message =
            "course id should be 5 characters 2 letters followed by 3 numbers like 'CS112'")
    private String managedCourseID;
}
