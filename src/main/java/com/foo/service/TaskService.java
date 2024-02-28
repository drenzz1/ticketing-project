package com.foo.service;


import com.foo.dto.ProjectDTO;
import com.foo.dto.TaskDTO;
import com.foo.entity.User;
import com.foo.enums.Status;

import java.util.List;

public interface TaskService {
    TaskDTO findById(Long id);
    List<TaskDTO>listAllTasks();
    void save(TaskDTO taskDTO);
    void update(TaskDTO taskDTO);
    void delete(Long id);
    int totalNonCompletedTask(String projectCode);
    int totalCompletedTask(String projectCode);

    void deleteByProject(ProjectDTO projectDTO);

    void completeByProject(ProjectDTO projectDTO);

    List<TaskDTO> listAllTasksByStatusIsNot(Status status);

    void updateStatus(TaskDTO task);

    List<TaskDTO> findAllTasksByStatus(Status status);

    List<TaskDTO> readAllByAssignedEmployee(User assignedEmployee);
}
