package com.foo.service.impl;

import com.foo.dto.ProjectDTO;
import com.foo.dto.TaskDTO;
import com.foo.entity.Task;
import com.foo.entity.User;
import com.foo.enums.Status;
import com.foo.mapper.ProjectMapper;
import com.foo.mapper.TaskMapper;
import com.foo.mapper.UserMapper;
import com.foo.repository.TaskRepository;
import com.foo.service.TaskService;
import com.foo.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ProjectMapper projectMapper;
    private final UserService userService;
    private final UserMapper userMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, ProjectMapper projectMapper, @Lazy UserService userService, UserMapper userMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.projectMapper = projectMapper;
        this.userService = userService;
        this.userMapper = userMapper;
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
            convertedTask.setTaskStatus(taskDTO.getTaskStatus()== null? task.get().getTaskStatus(): taskDTO.getTaskStatus());
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

    @Override
    public void deleteByProject(ProjectDTO projectDTO) {
       List<TaskDTO>list = listAllByProject(projectDTO);
       list.forEach(taskDTO -> delete(taskDTO.getId()));
    }

    @Override
    public void completeByProject(ProjectDTO projectDTO) {
        List<TaskDTO>list = listAllByProject(projectDTO);
        list.forEach(taskDTO -> {
            taskDTO.setTaskStatus(Status.COMPLETE);
            update(taskDTO);
        });
    }

    @Override
    public List<TaskDTO> listAllTasksByStatusIsNot(Status status) {
        User loggedInUser = userMapper.convertToEntity(userService.findByUsername("john@employee.com"));
        List<Task>list = taskRepository.findAllByTaskStatusIsNotAndAssignedEmployee(status,loggedInUser);
        return list.stream().map(taskMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void updateStatus(TaskDTO taskDTO) {
        Optional<Task> task=taskRepository.findById(taskDTO.getId());
        if (task.isPresent()){
            task.get().setTaskStatus(taskDTO.getTaskStatus());
            taskRepository.save(task.get());
        }
    }

    @Override
    public List<TaskDTO> findAllTasksByStatus(Status status) {
        User loggedInUser = userMapper.convertToEntity(userService.findByUsername("john@employee.com"));
        List<Task>list = taskRepository.findAllByTaskStatusAndAssignedEmployee(status,loggedInUser);
        return list.stream().map(taskMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> readAllByAssignedEmployee(User assignedEmployee) {
        List<Task>list = taskRepository.findAllByAssignedEmployee(assignedEmployee);
        return list.stream().map(taskMapper::convertToDto).collect(Collectors.toList());
    }

    private List<TaskDTO>listAllByProject(ProjectDTO projectDTO){
        List<Task>list = taskRepository.findAllByProject(projectMapper.convertToEntity(projectDTO));
        return list.stream().map(taskMapper::convertToDto).collect(Collectors.toList());
    }


}
