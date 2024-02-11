package org.foo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.foo.utils.Gender;
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    @NotBlank
    @Size(max = 15,min = 2)
    private String firstName;
    @NotBlank
    @Size(max = 15,min = 2)
    private String lastName;
    @NotBlank
    @Email
    private String username;
    @NotBlank
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,}")
    private String password;
    @NotNull
    private String confirmPassWord;
    private boolean enabled;
    @NotBlank
    @Pattern(regexp = "^\\d{10}$")
    private String phone;
    @NotNull
    private RoleDTO role;
    @NotNull
    private Gender gender;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        checkConfirmPassWord();
    }

    public String getConfirmPassword() {
        return confirmPassWord;
    }

    public void setConfirmPassWord(String confirmPassWord) {
        this.confirmPassWord = confirmPassWord;
        checkConfirmPassWord();
    }

    private void checkConfirmPassWord() {
        if(this.password == null || this.confirmPassWord == null){
            return;
        }else if(!this.password.equals(confirmPassWord)){
            this.confirmPassWord = null;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}


