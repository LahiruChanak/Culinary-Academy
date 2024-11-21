package lk.ijse.culinaryacademy.bo.custom;

import lk.ijse.culinaryacademy.bo.SuperBO;

import java.sql.SQLException;

public interface CredentialBO extends SuperBO {
    boolean checkLoginCredential(String email, String password);

    boolean checkRegisterCredential(String name, String email, String otp, String password, String confirmPW) throws SQLException;

    boolean updatePassword(String email, String newPW) throws SQLException;

    String getUsrName(String username) throws SQLException;
}
