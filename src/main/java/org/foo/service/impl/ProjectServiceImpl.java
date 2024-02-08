package org.foo.service.impl;

import org.foo.dto.ProjectDTO;
import org.foo.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {

    @Override
    public ProjectDTO save(ProjectDTO object) {
        return super.save(object.getProjectCode(),object);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(ProjectDTO object) {
        super.update(object.getProjectCode(),object);
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
    }

    @Override
    public ProjectDTO findById(String id) {
        return super.findById(id);
    }
}