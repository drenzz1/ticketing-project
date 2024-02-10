package org.foo.controller;

import org.foo.dto.ProjectDTO;
import org.foo.service.ProjectService;
import org.foo.service.UserService;
import org.foo.utils.Status;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String seeProjects(Model model){

        model.addAttribute("project",new ProjectDTO());
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("managers",userService.findMenagers());


        return "/project/create";
    }
    @PostMapping("/create")
    public String createProject(ProjectDTO projectDTO){
        projectService.save(projectDTO);
        return "redirect:/project/create";
    }
    @GetMapping("/update")
    public String editPost(){
        return "/project/update";
    }
    @GetMapping("/delete/{projectCode}")
    public String deletePost(@PathVariable("projectCode")String projectCode){
        projectService.deleteById(projectCode);
        return "redirect:/project/create";
    }
    @GetMapping("/complete/{projectCode}")
    public String completePost(@PathVariable("projectCode")String projectCode){
        projectService.findById(projectCode).setProjectStatus(Status.COMPLETE);
        return "redirect:/project/create";
    }

}