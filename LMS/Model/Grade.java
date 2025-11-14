package com.lab7.LMS.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Grade {
    @NotBlank(message = "student id should not be empty")
    @Size(min = 4, max = 4, message = "student id should be exactly 4")
    @Pattern(regexp = "^[0-9]*$", message = "student id should only be numbers")
    private String studentID;
    @NotBlank(message = "course id should not be empty")
    @Pattern(regexp = "^[a-zA-Z]{2}\\d{3}$", message =
            "course id should be 5 characters 2 letters followed by 3 numbers like 'CS112'")
    private String courseID;
    @NotNull(message = "grade should not be empty")
    @Positive(message = "grade should be a positive number")
    private double grade;


}
