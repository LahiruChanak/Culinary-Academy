package lk.ijse.culinaryacademy.bo.custom.impl;

import lk.ijse.culinaryacademy.bo.custom.CourseBO;
import lk.ijse.culinaryacademy.dto.CourseDTO;

import java.util.List;

public class CourseBOImpl implements CourseBO {
    @Override
    public boolean addCourse(CourseDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean updateCourse(CourseDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean deleteCourse(String id) throws Exception {
        return false;
    }

    @Override
    public CourseDTO searchCourse(String id) throws Exception {
        return null;
    }

    @Override
    public String generateNewCourseId() throws Exception {
        return "";
    }

    @Override
    public String currentCourseId() throws Exception {
        return "";
    }

    @Override
    public List<String> getCoordinatorIds() throws Exception {
        return List.of();
    }

    @Override
    public List<CourseDTO> getAllCourses() throws Exception {
        return List.of();
    }
}
