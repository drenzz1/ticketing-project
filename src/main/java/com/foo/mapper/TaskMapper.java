package com.foo.mapper;


import com.foo.dto.TaskDTO;
import com.foo.entity.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    private final ModelMapper modelMapper;

    public TaskMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public Task convertToEntity(TaskDTO taskDTO){
        return modelMapper.map(taskDTO, Task.class);
    }
    public TaskDTO convertToDto(Task entity){
        return modelMapper.map(entity, TaskDTO.class);
    }
}
