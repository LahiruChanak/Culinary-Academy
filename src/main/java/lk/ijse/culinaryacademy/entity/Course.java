package lk.ijse.culinaryacademy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Course {
    private String courseId;
    private String courseName;
    private String description;
    private int duration;
    private double fee;
    private String coordinatorId;
}