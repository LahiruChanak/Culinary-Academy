package lk.ijse.culinaryacademy.dao.custom.Impl;

import lk.ijse.culinaryacademy.dao.custom.UserDAO;
import lk.ijse.culinaryacademy.entity.User;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(User user) throws Exception {
        return false;
    }

    @Override
    public boolean update(User user) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public boolean changeEmail(String currentEmail, String newEmail, String confirmEmail) throws Exception {
        return false;
    }

    @Override
    public boolean changePassword(String currentPassword, String newPassword, String confirmPassword) throws Exception {
        return false;
    }

    @Override
    public User searchById(String userId) throws Exception {
        return null;
    }

    @Override
    public List<User> getAll() throws Exception {
        return List.of();
    }

    @Override
    public List<String> getIds() throws Exception {
        return List.of();
    }

    @Override
    public String currentId() throws Exception {
        return "";
    }
}
