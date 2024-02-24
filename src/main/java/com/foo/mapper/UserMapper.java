package com.foo.mapper;

import com.foo.dto.UserDTO;
import com.foo.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public User convertToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO,User.class);
    }
    public UserDTO convertToDto(User user){
        return modelMapper.map(user,UserDTO.class);
    }
}
