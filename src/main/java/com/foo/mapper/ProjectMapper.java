package com.foo.mapper;

import com.foo.dto.ProjectDTO;
import com.foo.entity.Project;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    private final ModelMapper modelMapper;

    public ProjectMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public Project convertToEntity(ProjectDTO projectDTO){
       return  modelMapper.map(projectDTO,Project.class);
    }
    public ProjectDTO convertToDto(Project project){
        return modelMapper.map(project,ProjectDTO.class);
    }
}
