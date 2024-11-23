package lk.ijse.culinaryacademy.view.tdm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CourseTm {

    private String courseId;
    private String courseName;
    private int duration;
    private double fee;
    private String description;
    private String coordinatorId;

}
