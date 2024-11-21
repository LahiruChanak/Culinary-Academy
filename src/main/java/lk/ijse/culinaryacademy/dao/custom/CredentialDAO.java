package lk.ijse.culinaryacademy.dao.custom;


import lk.ijse.culinaryacademy.dao.SuperDAO;

import java.sql.SQLException;

public interface CredentialDAO extends SuperDAO {

    boolean checkLoginCredential(String email, String password) throws SQLException;

    boolean checkRegisterCredential(String name, String email, String password, String confirmPassword) throws SQLException;

    boolean updatePassword(String email, String newPassword) throws SQLException;

    String getUsrName(String email) throws SQLException;
}
