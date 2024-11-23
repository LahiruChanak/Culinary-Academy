package lk.ijse.culinaryacademy.bo.custom;

import lk.ijse.culinaryacademy.bo.SuperBO;
import lk.ijse.culinaryacademy.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {

    boolean addStudent(StudentDTO dto) throws Exception;

    boolean updateStudent(StudentDTO dto) throws Exception;

    boolean deleteStudent(String id) throws Exception;

    StudentDTO searchStudent(String id) throws Exception;

    String generateNewStudentId() throws Exception;

    String currentStudentId() throws Exception;

    List<StudentDTO> getAllStudents() throws Exception;
}
