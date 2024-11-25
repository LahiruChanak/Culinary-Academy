package lk.ijse.culinaryacademy.dao.custom.Impl;


import lk.ijse.culinaryacademy.dao.custom.CourseDAO;
import lk.ijse.culinaryacademy.entity.Course;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    @Override
    public boolean add(Course course) throws Exception {
        return false;
    }

    @Override
    public boolean update(Course course) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public String currentId() throws Exception {
        return "";
    }

    @Override
    public List<Course> getAll() {
        return List.of();
    }

    @Override
    public List<String> getIds() throws Exception {
        return List.of();
    }

    @Override
    public Course searchById(String courseId) throws Exception {
        return null;
    }

}
