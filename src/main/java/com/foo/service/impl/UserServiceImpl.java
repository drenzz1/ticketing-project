package com.foo.service.impl;

import com.foo.dto.UserDTO;
import com.foo.mapper.UserMapper;
import com.foo.repository.UserRepository;
import com.foo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public List<UserDTO> listAllUsers() {
       return  userRepository.findAll().stream().map(userMapper::convertToDto).toList();
    }

    @Override
    public UserDTO findByUsername(String username) {
        return null;
    }

    @Override
    public void save(UserDTO userDTO) {
        userRepository.save(userMapper.convertToEntity(userDTO));
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteByUsername(String username) {

    }
}
