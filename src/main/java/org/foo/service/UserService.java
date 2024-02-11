package org.foo.service;

import org.foo.dto.UserDTO;

import java.util.List;

public interface UserService extends CrudService<UserDTO,String>{
    List<UserDTO>findMenagers();
    List<UserDTO>findEmployees();
}
