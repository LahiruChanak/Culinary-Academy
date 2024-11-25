package lk.ijse.culinaryacademy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Enrolment {

    private String enrolmentId;
    private String studentId;
    private String courseId;
    private Date enrolledDate;

}
