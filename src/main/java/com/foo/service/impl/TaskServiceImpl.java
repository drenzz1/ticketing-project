package com.foo.service.impl;

import com.foo.dto.TaskDTO;
import com.foo.mapper.TaskMapper;
import com.foo.repository.TaskRepository;
import com.foo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskDTO findById(Long id) {
        return null;
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        return null;
    }

    @Override
    public void save(TaskDTO taskDTO) {

    }

    @Override
    public void update(TaskDTO taskDTO) {

    }

    @Override
    public void delete(Long id) {

    }
}
