package lk.ijse.culinaryacademy.bo.custom;

import lk.ijse.culinaryacademy.bo.SuperBO;
import lk.ijse.culinaryacademy.dto.UserDTO;

import java.util.List;

public interface UserBO extends SuperBO {
    boolean addUser(UserDTO dto) throws Exception;

    boolean updateUser(UserDTO dto) throws Exception;

    boolean deleteUser(String id) throws Exception;

    List<UserDTO> getAllUsers() throws Exception;

    List<String> getCoordinatorIds() throws Exception;

    String currentUserId() throws Exception;

    boolean changeEmail(String currentEmail, String newEmail, String confirmEmail) throws Exception;

    boolean changePassword(String currentPassword, String newPassword, String confirmPassword) throws Exception;

    UserDTO searchByUserId(String userId) throws Exception;
}
