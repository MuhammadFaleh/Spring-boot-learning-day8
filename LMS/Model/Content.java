package com.lab7.LMS.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Content {
    @NotBlank(message = "content id should not be empty")
    @Size(min = 5, max = 5, message = "content id should be exactly 5")
    @Pattern(regexp = "^[0-9]*$", message = "content id should only be numbers")
    private String id;
    @NotBlank(message = "course id should not be empty")
    @Pattern(regexp = "^[a-zA-Z]{2}\\d{3}$", message =
            "course id should be 5 characters 2 letters followed by 3 numbers like 'CS112'")
    private String courseID;
    @NotBlank(message = "title must not be empty")
    @Size(max = 100, message = "title must not be longer than 100 characters")
    private String title;
    @NotBlank(message = "body can not be empty")
    @Size(min = 10, max=500, message = "body must be between 10 and 500 characters")
    private String body;
}
