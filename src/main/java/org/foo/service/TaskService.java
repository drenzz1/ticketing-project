package org.foo.service;

import org.foo.dto.TaskDTO;
import org.foo.dto.UserDTO;
import org.foo.utils.Status;

import java.util.List;

public interface TaskService  extends CrudService<TaskDTO,Long>{
    List<TaskDTO> findTasksByManager(UserDTO manager);
    List<TaskDTO> findAllTasksByStatus(Status Status);

    List<TaskDTO> findAllTasksByStatusIsNot(Status Status);

    void updateStatus(TaskDTO taskDTO);

}
