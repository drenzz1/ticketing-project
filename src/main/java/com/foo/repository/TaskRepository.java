package com.foo.repository;

import com.foo.entity.Project;
import com.foo.entity.Task;
import com.foo.entity.User;
import com.foo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    @Query("select count(t) from Task t where t.project.projectCode = ?1 and t.taskStatus<>'COMPLETE'")
    public int totalNonCompletedTasks(String projectCode);
    @Query(value = "SELECT COUNT(*) FROM tasks t JOIN projects p ON t.project_id = p.id WHERE p.project_code = ?1 AND t.task_status = 'COMPLETE'", nativeQuery = true)
    public int totalCompletedTasks(String projectCode);
    List<Task> findAllByProject(Project project);

    List<Task> findAllByTaskStatusIsNotAndAssignedEmployee(Status status, User loggedInUser);

    List<Task> findAllByTaskStatusAndAssignedEmployee(Status status, User loggedInUser);

    List<Task> findAllByAssignedEmployee(User assignedEmployee);
}
