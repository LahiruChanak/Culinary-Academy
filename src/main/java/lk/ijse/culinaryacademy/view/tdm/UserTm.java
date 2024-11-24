package lk.ijse.culinaryacademy.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserTm {

    private String userId;
    private String name;
    private String email;
    private String contact;
    private String address;
    private String password;
    private String role;


    public UserTm(String userId, String name, String email, String role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
