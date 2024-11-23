package lk.ijse.culinaryacademy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CourseDTO {

    private String courseId;
    private String courseName;
    private int duration;
    private double fee;
    private String description;
    private String coordinatorId;

}

