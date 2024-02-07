package org.foo.controller;

import org.foo.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/create")
    public String createUser(Model model){
        model.addAttribute("user",new UserDto());

        return "user/create";
    }

}
