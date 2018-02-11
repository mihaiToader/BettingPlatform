package com.mt.bettingPlatform.controller;

import com.mt.bettingPlatform.repository.UserRepository;
import com.mt.bettingPlatform.domain.User;
import com.mt.bettingPlatform.domain.dto.UserDto;
import com.mt.bettingPlatform.service.iService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registerSubmit")
    public String registerSubmit(@Valid UserDto cFto, Model model, Principal principal) {
        // TODO make use of valid annotation
        if (!cFto.getPassword().equals(cFto.getRepeatPassword())){
            model.addAttribute("tUser", new UserDto());
            model.addAttribute("error", true);
            return "views/register";
        }
        if (userService.findByName(cFto.getName()) != null){
            model.addAttribute("tUser", new UserDto());
            model.addAttribute("error2", true);
            return "views/register";
        }

        User user = null;
        try {
            user = new User(cFto.getPassword(), cFto.getName());
            userService.saveUser(user);
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "views/login";
    }

    @GetMapping("/register")
    public String register(ModelMap model) {
        model.addAttribute("tUser", new UserDto());
        return "views/register";
    }
}
