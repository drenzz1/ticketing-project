package com.foo.mapper;

import com.foo.dto.RoleDTO;
import com.foo.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    private final ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Role convertToEntity(RoleDTO roleDTO){
        return modelMapper.map(roleDTO,Role.class);
    }
    public RoleDTO convertToDto(Role role){
        return modelMapper.map(role,RoleDTO.class);
    }
}
