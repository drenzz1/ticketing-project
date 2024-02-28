package com.foo.entity;

import com.foo.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Where;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@Where(clause = "is_deleted=false")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    @Column(unique = true,nullable = false)
    private String userName;
    private String passWord;
    private boolean enabled;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;


}