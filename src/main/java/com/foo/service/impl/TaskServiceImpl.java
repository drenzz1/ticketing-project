package com.foo.service.impl;

import com.foo.dto.ProjectDTO;
import com.foo.dto.TaskDTO;
import com.foo.entity.Task;
import com.foo.enums.Status;
import com.foo.mapper.TaskMapper;
import com.foo.repository.TaskRepository;
import com.foo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        return taskMapper.convertToDto(taskRepository.findById(id).get());
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        return taskRepository.findAll().stream().map(taskMapper::convertToDto).toList();
    }

    @Override
    public void save(TaskDTO taskDTO) {
        taskDTO.setTaskStatus(Status.OPEN);
        taskDTO.setAssignedDate(LocalDate.now());
        taskRepository.save(taskMapper.convertToEntity(taskDTO));
    }

    @Override
    public void update(TaskDTO taskDTO) {
        Optional<Task>task = taskRepository.findById(taskDTO.getId());
        Task convertedTask=taskMapper.convertToEntity(taskDTO);

        if (task.isPresent()){
            convertedTask.setId(task.get().getId());
            convertedTask.setTaskStatus(task.get().getTaskStatus());
            convertedTask.setAssignedDate(task.get().getAssignedDate());
            taskRepository.save(convertedTask);
        }

    }

    @Override
    public void delete(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()){
            task.get().setIsDeleted(true);
            taskRepository.save(task.get());
        }

    }

    @Override
    public int totalNonCompletedTask(String projectCode) {
        return taskRepository.totalNonCompletedTasks(projectCode);
    }

    @Override
    public int totalCompletedTask(String projectCode) {
        return taskRepository.totalCompletedTasks(projectCode);
    }


}
