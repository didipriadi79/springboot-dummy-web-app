package com.priadi.web.dummy.controller;

import com.priadi.web.dummy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("allUser", userService.getAllUser());
        return "index";
    }
}
