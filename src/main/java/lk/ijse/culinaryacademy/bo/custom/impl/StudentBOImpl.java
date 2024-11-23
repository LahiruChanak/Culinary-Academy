package lk.ijse.culinaryacademy.bo.custom.impl;

import lk.ijse.culinaryacademy.bo.custom.StudentBO;
import lk.ijse.culinaryacademy.dto.StudentDTO;

import java.util.List;

public class StudentBOImpl implements StudentBO {
    @Override
    public boolean addStudent(StudentDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean deleteStudent(String id) throws Exception {
        return false;
    }

    @Override
    public StudentDTO searchStudent(String id) {
        return null;
    }

    @Override
    public String generateNewStudentId() {
        return "";
    }

    @Override
    public String currentStudentId() throws Exception {
        return "";
    }

    @Override
    public List<StudentDTO> getAllStudents() throws Exception {
        return List.of();
    }
}
