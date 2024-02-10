package org.foo.service.impl;

import org.foo.dto.ProjectDTO;
import org.foo.dto.TaskDTO;
import org.foo.dto.UserDTO;
import org.foo.service.ProjectService;
import org.foo.service.TaskService;
import org.foo.utils.Status;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {
    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ProjectDTO save(ProjectDTO object)
    {
        if (object.getProjectStatus()==null){
            object.setProjectStatus(Status.OPEN);
        }
        return super.save(object.getProjectCode(),object);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(ProjectDTO object) {
        if (object.getProjectStatus()==null){
            object.setProjectStatus(findById(object.getProjectCode()).getProjectStatus());
        }
        super.update(object.getProjectCode(),object);
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
    }

    @Override
    public ProjectDTO findById(String id) {
        return super.findById(id);
    }

    @Override
    public void complete(String id) {
        super.findById(id).setProjectStatus(Status.COMPLETE);
    }

    @Override
    public List<ProjectDTO> findAllNonCompletedProjects() {
        return findAll().stream().filter(projectDTO -> !projectDTO.getProjectStatus().equals(Status.COMPLETE)).collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {

        List<ProjectDTO> projectList =
                findAll()
                        .stream()
                        .filter(project -> project.getAssignedManager().equals(manager))
                        .map( project -> {


                            List<TaskDTO> taskList = taskService.findTasksByManager(manager);

                            int completeTaskCounts = (int)taskList.stream().filter(t ->  t.getProjectDTO().equals(project) && t.getTaskStatus() == Status.COMPLETE).count();

                            int unfinishedTaskCounts = (int)taskList.stream().filter(t ->  t.getProjectDTO().equals(project) && t.getTaskStatus() != Status.COMPLETE).count();

                            project.setCompleteTaskCounts(completeTaskCounts);
                            project.setUnfinishedTaskCounts(unfinishedTaskCounts);

                            return project;

                        }).collect(Collectors.toList());


        return projectList;
    }
}
