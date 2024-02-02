package com.isa.webapp.controller;

import com.isa.webapp.model.User;
import com.isa.webapp.model.UserRole;
import com.isa.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/manage-users")
    public String showManageUsersPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<User> users = userService.getAllUsers();
        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));
        model.addAttribute("users", users);
        model.addAttribute("isAdmin", isAdmin);
        return "manage_users";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/manage-users";
    }

    @GetMapping("/edit-user/{userId}")
    public String showEditUserPage(@PathVariable Long userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "edit_user";
    }

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("user") User updatedUser) {
        userService.updateUser(updatedUser);
        return "redirect:/admin/manage-users";
    }

    @GetMapping("/approve-roles")
    public String showApproveRolesPage(Model model) {
        List<User> unapprovedUsers = userService.getUnapprovedUsers();
        model.addAttribute("unapprovedUsers", unapprovedUsers);
        return "approve_roles";
    }

    @PostMapping("/approve-roles")
    public String approveUserRoles(
            @RequestParam List<Long> userIds,
            @RequestParam(required = false) List<String> roles
    ) {
        if (roles != null && !roles.isEmpty()) {
            List<UserRole> userRoles = roles.stream().map(UserRole::valueOf).collect(Collectors.toList());
            userIds.forEach(userId -> userService.approveUserRoles(userId, userRoles));
        }
        return "redirect:/admin/approve-roles";
    }
}

