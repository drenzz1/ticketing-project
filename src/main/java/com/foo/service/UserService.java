package com.foo.service;

import com.foo.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<UserDTO> listAllUsers();
    UserDTO findByUsername(String username);
    void save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    void deleteByUsername(String username);
    void delete(String username);
}
