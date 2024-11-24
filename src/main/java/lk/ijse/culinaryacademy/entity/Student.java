package lk.ijse.culinaryacademy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Student {
    private String studentId;
    private String name;
    private String email;
    private String contact;
    private String address;
    private Date enrolledDate;
}