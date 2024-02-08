package org.foo.controller;

import org.foo.dto.UserDto;
import org.foo.service.RoleService;
import org.foo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    UserService userService;
    RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/create")
    public String createUser(Model model){
        model.addAttribute("user",new UserDto());
        model.addAttribute("roles",roleService.findAll());
        return "user/create";
    }

}
