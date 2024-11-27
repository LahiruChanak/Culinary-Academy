package lk.ijse.culinaryacademy.dao.custom;

import lk.ijse.culinaryacademy.dao.CrudDAO;
import lk.ijse.culinaryacademy.entity.User;

public interface UserDAO extends CrudDAO<User> {

    boolean changeEmail(String currentEmail, String newEmail, String confirmEmail) throws Exception;

    boolean changePassword(String currentPassword, String newPassword, String confirmPassword) throws Exception;

    boolean checkLogin(String email, String password) throws Exception;

    boolean checkRegister(String username, String name, String email, String password, String confirmPassword) throws Exception;

    String getUserName(String email) throws Exception;

    User searchByName(String name) throws Exception;
}
