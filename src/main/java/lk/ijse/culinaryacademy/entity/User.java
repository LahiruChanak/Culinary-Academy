
package lk.ijse.culinaryacademy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id" , length = 100)
    private String userId;

    @Column(name = "name" , length = 100)
    private String name;

    @Column(name = "email" , length = 100)
    private String email;

    @Column(name = "password" , length = 100)
    private String password;

    @Column(name = "role" , length = 100)
    private String role;

}
