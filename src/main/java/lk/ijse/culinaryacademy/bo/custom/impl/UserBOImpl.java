package lk.ijse.culinaryacademy.bo.custom.impl;

import lk.ijse.culinaryacademy.bo.custom.UserBO;
import lk.ijse.culinaryacademy.dao.DAOFactory;
import lk.ijse.culinaryacademy.dao.custom.UserDAO;
import lk.ijse.culinaryacademy.dto.UserDTO;
import lk.ijse.culinaryacademy.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean addUser(UserDTO dto) throws Exception {
        return userDAO.add(new User(
                dto.getUserId(),
                dto.getName(),
                dto.getEmail(),
                dto.getRole(),
                dto.getPassword())
        );
    }

    @Override
    public boolean updateUser(UserDTO dto) throws Exception {
        return userDAO.update(new User(
                dto.getUserId(),
                dto.getName(),
                dto.getEmail(),
                dto.getRole(),
                dto.getPassword())
        );
    }

    @Override
    public boolean deleteUser(String id) throws Exception {
        return userDAO.delete(id);
    }

    @Override
    public List<UserDTO> getAllUsers() throws Exception {
        List<UserDTO> allUsers = new ArrayList<>();
        List<User> all = userDAO.getAll();

        for (User u : all) {
            allUsers.add(new UserDTO(
                    u.getUserId(),
                    u.getName(),
                    u.getEmail(),
                    u.getRole(),
                    u.getPassword())
            );
        }
        return allUsers;
    }

    @Override
    public List<String> getCoordinatorIds() throws Exception {
        return userDAO.getIds();
    }

    @Override
    public String currentUserId() throws Exception {
        return userDAO.currentId();
    }

    @Override
    public boolean changeEmail(String currentEmail, String newEmail, String confirmEmail) throws Exception {
        return userDAO.changeEmail(currentEmail, newEmail, confirmEmail);
    }

    @Override
    public boolean changePassword(String currentPassword, String newPassword, String confirmPassword) throws Exception {
        return userDAO.changePassword(currentPassword, newPassword, confirmPassword);
    }

    @Override
    public UserDTO searchByUserId(String userId) throws Exception {
        User u = userDAO.searchById(userId);
        return new UserDTO(
                u.getUserId(),
                u.getName(),
                u.getEmail(),
                u.getRole(),
                u.getPassword()
        );
    }
}
