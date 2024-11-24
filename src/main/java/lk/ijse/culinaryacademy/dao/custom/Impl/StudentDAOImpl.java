package lk.ijse.culinaryacademy.dao.custom.Impl;


import lk.ijse.culinaryacademy.dao.custom.StudentDAO;
import lk.ijse.culinaryacademy.entity.Student;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean add(Student student) throws Exception {
        return false;
    }

    @Override
    public boolean update(Student student) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String studentId) throws Exception {
        return false;
    }

    @Override
    public Student search(String id) throws Exception {
        return null;
    }

    @Override
    public String currentId() throws Exception {
        return "";
    }

    @Override
    public List<Student> getAll() throws Exception {
        return List.of();
    }
}
