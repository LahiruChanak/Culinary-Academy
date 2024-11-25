package lk.ijse.culinaryacademy.dao.custom;

import lk.ijse.culinaryacademy.dao.CrudDAO;
import lk.ijse.culinaryacademy.entity.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User> {

    boolean changeEmail(String currentEmail, String newEmail, String confirmEmail) throws Exception;

    boolean changePassword(String currentPassword, String newPassword, String confirmPassword) throws Exception;
}
