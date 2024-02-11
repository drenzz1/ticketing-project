package org.foo.service.impl;

import org.foo.dto.UserDTO;
import org.foo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl extends AbstractMapService<UserDTO,String>implements UserService {
    @Override
    public UserDTO save(UserDTO object) {
        return super.save(object.getUsername(), object);
    }

    @Override
    public List<UserDTO> findAll() {
        return super.findAll();
    }

    @Override
    public UserDTO findById(String s) {
        return super.findById(s);
    }

    @Override
    public void deleteById(String s) {
        super.deleteById(s);
    }

    @Override
    public void update(UserDTO object) {
        super.update(object.getUsername(),object);
    }

    @Override
    public List<UserDTO> findMenagers() {
        return super.findAll().stream().filter(menager->menager.getRole().getId()==2).toList();
    }

    @Override
    public List<UserDTO> findEmployees() {
        return super.findAll().stream().filter(employee->employee.getRole().getId()==3).toList();
    }
}
