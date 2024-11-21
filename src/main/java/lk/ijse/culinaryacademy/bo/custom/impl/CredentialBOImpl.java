package lk.ijse.culinaryacademy.bo.custom.impl;

import lk.ijse.culinaryacademy.bo.custom.CredentialBO;

import java.sql.SQLException;

public class CredentialBOImpl implements CredentialBO {

    public static String userName;

    @Override
    public boolean checkLoginCredential(String email, String password) {
        return false;
    }

    @Override
    public boolean checkRegisterCredential(String name, String email, String otp, String password, String confirmPW) throws SQLException {
        return false;
    }

    @Override
    public boolean updatePassword(String email, String newPW) throws SQLException {
        return false;
    }

    @Override
    public String getUsrName(String username) throws SQLException {
        return "";
    }
}
