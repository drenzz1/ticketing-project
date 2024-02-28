package com.foo.service;

import com.foo.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {
    ProjectDTO getByProjectCode(String code);
    List<ProjectDTO> listAllProjects();
    void save(ProjectDTO projectDTO);
    void update(ProjectDTO projectDTO);
    void delete(String code);
    void complete(String projectCode);
    List<ProjectDTO>listAllProjectDetails();

}
