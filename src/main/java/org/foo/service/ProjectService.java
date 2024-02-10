package org.foo.service;

import org.foo.dto.ProjectDTO;

public interface ProjectService extends CrudService<ProjectDTO,String> {
    void complete(String id);
}
