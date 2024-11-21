package lk.ijse.culinaryacademy.dao.custom.Impl;


import lk.ijse.culinaryacademy.dao.custom.CredentialDAO;

import java.sql.SQLException;

public class CredentialDAOImpl implements CredentialDAO {
    @Override
    public boolean checkLoginCredential(String email, String password) throws SQLException {
        return false;
    }

    @Override
    public boolean checkRegisterCredential(String name, String email, String password, String confirmPassword) throws SQLException {
        return false;
    }

    @Override
    public boolean updatePassword(String email, String newPassword) throws SQLException {
        return false;
    }

    @Override
    public String getUsrName(String email) throws SQLException {
        return "";
    }
}
