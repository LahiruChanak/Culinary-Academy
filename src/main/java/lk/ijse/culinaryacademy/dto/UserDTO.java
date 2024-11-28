package lk.ijse.culinaryacademy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserDTO {

    private String username;
    private String name;
    private String email;
    private String role;
    private String password;

    public UserDTO(String userId, String name, String email, String role) {
        this.username = userId;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
