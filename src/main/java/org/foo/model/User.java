package org.foo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.foo.utils.Gender;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User extends BaseEntity{
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean enabled;
    private String phone;
    private Role role;
    private Gender gender;

    public User(Long id, LocalDateTime insertDateTime, Long insertUserId, LocalDateTime lastUpdateDateTime, Long lastUpdateUserId, String firstName, String lastName, String userName, String password, boolean enabled, String phone, Role role, Gender gender) {
        super(id, insertDateTime, insertUserId, lastUpdateDateTime, lastUpdateUserId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = userName;
        this.password = password;
        this.enabled = enabled;
        this.phone = phone;
        this.role = role;
        this.gender = gender;
    }
}

