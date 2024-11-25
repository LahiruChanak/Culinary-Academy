
package lk.ijse.culinaryacademy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private String userId;
    private String name;
    private String email;
    private String password;
    private String role;

}
