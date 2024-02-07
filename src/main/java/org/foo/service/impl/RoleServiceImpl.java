package org.foo.service.impl;

import org.foo.dto.RoleDTO;
import org.foo.service.RoleService;

import java.util.List;

public class RoleServiceImpl extends AbstractMapService<Long,RoleDTO> implements RoleService {
    @Override
    public RoleDTO save(RoleDTO object) {
        return super.save(object.getId(),object);
    }

    @Override
    public List<RoleDTO> findAll() {
        return super.findAll();
    }

    @Override
    public RoleDTO findById(Long aLong) {
        return super.findById(aLong);
    }


    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }
}
