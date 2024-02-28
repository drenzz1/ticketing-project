package com.foo.service;


import com.foo.dto.TaskDTO;

import java.util.List;

public interface TaskService {
    TaskDTO findById(Long id);
    List<TaskDTO>listAllTasks();
    void save(TaskDTO taskDTO);
    void update(TaskDTO taskDTO);
    void delete(Long id);
    int totalNonCompletedTask(String projectCode);
    int totalCompletedTask(String projectCode);

}
