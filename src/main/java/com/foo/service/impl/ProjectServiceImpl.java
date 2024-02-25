package com.foo.service.impl;

import com.foo.dto.ProjectDTO;
import com.foo.enums.Status;
import com.foo.mapper.ProjectMapper;
import com.foo.repository.ProjectRepository;
import com.foo.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    @Override
    public ProjectDTO getByProjectCode(String code) {
      return    projectMapper.convertToDto(projectRepository.findByProjectCode(code));
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        return projectRepository.findAll().stream().map(projectMapper::convertToDto).toList();
    }

    @Override
    public void save(ProjectDTO projectDTO) {
        projectDTO.setProjectStatus(Status.OPEN);
      projectRepository.save(projectMapper.convertToEntity(projectDTO));
    }

    @Override
    public void update(ProjectDTO projectDTO) {

    }

    @Override
    public void delete(ProjectDTO projectDTO) {

    }
}
