package lk.ijse.culinaryacademy.dao.custom;


import lk.ijse.culinaryacademy.dao.CrudDAO;
import lk.ijse.culinaryacademy.entity.Student;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {

    boolean add(Student student) throws Exception;

    boolean update(Student student) throws Exception;

    boolean delete(String studentId) throws Exception;

    Student search(String id) throws Exception;

    String currentId() throws Exception;

    List<Student> getAll() throws Exception;
}
