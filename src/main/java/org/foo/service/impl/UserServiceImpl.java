package org.foo.service.impl;

import org.foo.dto.UserDto;
import org.foo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl extends AbstractMapService<String,UserDto>implements UserService {
    @Override
    public UserDto save(UserDto object) {
        return super.save(object.getUsername(), object);
    }

    @Override
    public List<UserDto> findAll() {
        return super.findAll();
    }

    @Override
    public UserDto findById(String s) {
        return super.findById(s);
    }

    @Override
    public void deleteById(String s) {
        super.deleteById(s);
    }
}
