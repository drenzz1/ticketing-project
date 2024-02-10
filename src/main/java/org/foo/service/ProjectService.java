package org.foo.service;

import org.foo.dto.ProjectDTO;
import org.foo.dto.UserDTO;

import java.util.List;

public interface ProjectService extends CrudService<ProjectDTO,String> {
    void complete(String id);

    List<ProjectDTO> findAllNonCompletedProjects();

    List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager);
}
