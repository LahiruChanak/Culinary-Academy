package lk.ijse.culinaryacademy.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "enrolment")
public class Enrolment {

    @Id
    @Column(name = "enrolment_id")
    private String enrolmentId;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "course_id")
    private String courseId;

    @Column(name = "enrolled_date")
    private Date enrolledDate;

    // Many-to-One relationship with Student
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    // Many-to-One relationship with Course
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;
}