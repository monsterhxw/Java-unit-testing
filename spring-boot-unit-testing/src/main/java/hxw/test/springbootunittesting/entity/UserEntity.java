package hxw.test.springbootunittesting.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "user")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

    private LocalDateTime registrationDate;

    public UserEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
