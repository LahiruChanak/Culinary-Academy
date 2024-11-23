package lk.ijse.culinaryacademy.bo.custom;

import lk.ijse.culinaryacademy.bo.SuperBO;
import lk.ijse.culinaryacademy.dto.CourseDTO;

import java.util.List;

public interface CourseBO extends SuperBO {
    boolean addCourse(CourseDTO dto) throws Exception;

    boolean updateCourse(CourseDTO dto) throws Exception;

    boolean deleteCourse(String id) throws Exception;

    CourseDTO searchCourse(String id) throws Exception;

    String generateNewCourseId() throws Exception;

    String currentCourseId() throws Exception;

    List<String> getCoordinatorIds() throws Exception;

    List<CourseDTO> getAllCourses() throws Exception;
}
