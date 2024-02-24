package com.foo.service.impl;

import com.foo.dto.RoleDTO;
import com.foo.mapper.RoleMapper;
import com.foo.repository.RoleRepository;
import com.foo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }



    @Override
    public List<RoleDTO> listAllRoles() {
       return roleRepository.findAll().stream().map(roleMapper::convertToDto).toList();
    }

    @Override
    public RoleDTO findById(Long id) {
        return null;
    }
}
