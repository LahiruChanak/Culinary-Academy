package lk.ijse.culinaryacademy.bo.custom.impl;

import lk.ijse.culinaryacademy.bo.custom.UserBO;
import lk.ijse.culinaryacademy.dto.UserDTO;

import java.util.List;

public class UserBOImpl implements UserBO {

    @Override
    public boolean addUser(UserDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean updateUser(UserDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean deleteUser(String id) throws Exception {
        return false;
    }

    @Override
    public List<UserDTO> getAllUsers() throws Exception {
        return List.of();
    }

    @Override
    public List<String> getCoordinatorIds() throws Exception {
        return List.of();
    }

    @Override
    public String currentUserId() throws Exception {
        return "";
    }
}
