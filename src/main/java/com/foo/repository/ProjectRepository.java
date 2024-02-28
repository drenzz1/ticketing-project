package com.foo.repository;

import com.foo.entity.Project;
import com.foo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    Project findByProjectCode(String code);
    List<Project> findAllByAssignedManager(User manager);
}
