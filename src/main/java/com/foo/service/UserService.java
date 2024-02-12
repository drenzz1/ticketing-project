package com.foo.service;

import com.foo.dto.UserDTO;

import java.util.List;

public interface UserService extends CrudService<UserDTO, String> {

    List<UserDTO> findManagers();

    List<UserDTO> findEmployees();

}
