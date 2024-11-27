package lk.ijse.culinaryacademy.dao.custom;

import lk.ijse.culinaryacademy.dao.CrudDAO;
import lk.ijse.culinaryacademy.entity.User;

public interface UserDAO extends CrudDAO<User> {

    boolean changeEmail(String currentEmail, String newEmail, String confirmEmail) throws Exception;

    boolean changePassword(String currentPassword, String newPassword, String confirmPassword) throws Exception;

    User checkLogin(String username, String password) throws Exception;

    boolean checkRegister(String username, String name, String email, String password, String confirmPassword, String role) throws Exception;

    String getUsrName(String username) throws Exception;

    User searchByName(String name) throws Exception;
}
