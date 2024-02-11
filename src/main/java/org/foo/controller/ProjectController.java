package org.foo.controller;

import org.foo.dto.ProjectDTO;
import org.foo.dto.UserDTO;
import org.foo.service.ProjectService;
import org.foo.service.UserService;
import org.foo.utils.Status;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    ProjectService projectService;
    UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createProject(Model model){
        model.addAttribute("project",new ProjectDTO());
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("managers",userService.findMenagers());
        return "/project/create";
    }
    @PostMapping("/create")
    public String insertProject(ProjectDTO projectDTO){
        projectService.save(projectDTO);
        return "redirect:/project/create";
    }
    @GetMapping("/delete/{projectCode}")
    public String deletePost(@PathVariable("projectCode")String projectCode){
        projectService.deleteById(projectCode);
        return "redirect:/project/create";
    }
    @GetMapping("/complete/{projectCode}")
    public String completePost(@PathVariable("projectCode")String projectCode){
        projectService.complete(projectCode);
        return "redirect:/project/create";
    }
    @GetMapping("/update/{projectCode}")
    public String editPost(@PathVariable("projectCode")String projecCode,Model model){
        model.addAttribute("project",projectService.findById(projecCode));
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("managers",userService.findMenagers());
        return "/project/update";

    }

    @PostMapping("/update")
    public String updateProject(ProjectDTO projectDTO){
        projectService.update(projectDTO);
        return "redirect:/project/create";
    }
    @GetMapping("/manager/project-status")
    public String getProjectByManager(Model model){

        UserDTO manager = userService.findById("john@gmail.com");

        List<ProjectDTO> projects = projectService.getCountedListOfProjectDTO(manager);

        model.addAttribute("projects",projects);

        return "/manager/project-status";
    }

    @GetMapping("/manager/complete/{projectCode}")
    public String managerCompleteProject(@PathVariable("projectCode") String projectCode) {
        projectService.complete(projectCode);
        return "redirect:/project/manager/project-status";
    }

}