package com.isa.webapp.controller;

import com.isa.webapp.service.IdGeneratorService;
import com.isa.webapp.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.isa.webapp.model.User;

import java.io.IOException;

@Controller
public class UserController {
    private final UserManager userManager;

    @Autowired
    private IdGeneratorService idGeneratorService;

    @Autowired
    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute User user) {
        user.setUserId(idGeneratorService.generateUniqueId());

        try {
            userManager.registerUser(user);
            return "registrationSuccessPage";
        } catch (IOException e) {
            e.printStackTrace();
            return "errorPage";
        }
    }

}
