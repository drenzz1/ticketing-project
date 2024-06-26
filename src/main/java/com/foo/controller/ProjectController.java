package com.foo.controller;

import com.foo.dto.ProjectDTO;
import com.foo.dto.UserDTO;
import com.foo.service.ProjectService;
import com.foo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String createProject(Model model) {

        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("projects", projectService.listAllProjectDetails());
        model.addAttribute("managers", userService.listAllByRole("Manager"));

        return "/project/create";

    }

    @PostMapping("/create")
    public String insertProject(@Valid @ModelAttribute("project") ProjectDTO project, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("projects", projectService.listAllProjectDetails());
            model.addAttribute("managers", userService.listAllByRole("Manager"));

            return "/project/create";

        }

        projectService.save(project);
        return "redirect:/project/create";

    }

    @GetMapping("/delete/{projectcode}")
    public String deleteProject(@PathVariable("projectcode") String projectcode) {
        projectService.delete(projectcode);
        return "redirect:/project/create";
    }

    @GetMapping("/complete/{projectcode}")
    public String completeProject(@PathVariable("projectcode") String projectcode) {
        projectService.complete(projectcode);
        return "redirect:/project/create";
    }

    @GetMapping("/update/{projectcode}")
    public String editProject(@PathVariable("projectcode") String projectcode, Model model) {

        model.addAttribute("project", projectService.getByProjectCode(projectcode));
        model.addAttribute("projects", projectService.listAllProjectDetails());
        model.addAttribute("managers", userService.listAllByRole("Manager"));

        return "/project/update";

    }

    @PostMapping("/update")
    public String updateProject(@Valid @ModelAttribute("project") ProjectDTO project, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("projects", projectService.listAllProjectDetails());
            model.addAttribute("managers", userService.listAllByRole("Manager"));

            return "/project/update";

        }

        projectService.update(project);
        return "redirect:/project/create";

    }

    @GetMapping("/manager/project-status")
    public String getProjectByManager(Model model) {

        List<ProjectDTO>projects= projectService.listAllProjectDetails();

        model.addAttribute("projects", projects);

        return "/manager/project-status";
    }

//    @GetMapping("/manager/complete/{projectCode}")
//    public String managerCompleteProject(@PathVariable("projectCode") String projectCode) {
//        projectService.complete(projectService.findById(projectCode));
//        return "redirect:/project/manager/project-status";
//    }

}
