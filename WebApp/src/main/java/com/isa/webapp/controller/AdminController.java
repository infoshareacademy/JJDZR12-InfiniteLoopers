package com.isa.webapp.controller;

import com.isa.webapp.model.User;
import com.isa.webapp.model.UserRole;
import com.isa.webapp.service.UserApprovalService;
import com.isa.webapp.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserApprovalService userApprovalService;
    private final UserManager userManager;

    @Autowired
    public AdminController(UserApprovalService userApprovalService, UserManager userManager) {
        this.userApprovalService = userApprovalService;
        this.userManager = userManager;
    }

    @GetMapping("/manage-users")
    public String showManageUsersPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<User> users = userManager.getAllUsers();
        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));
        model.addAttribute("users", users);
        model.addAttribute("isAdmin", isAdmin);
        return "manage_users";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam String userId) throws IOException {
        userManager.deleteUser(userId);
        return "redirect:/admin/manage-users";
    }

    @GetMapping("/edit-user/{userId}")
    public String showEditUserPage(@PathVariable String userId, Model model) {
        User user = userManager.getUserById(userId);
        model.addAttribute("user", user);
        return "edit_user";
    }

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("user") User updatedUser) throws IOException {
        userManager.updateUser(updatedUser);
        return "redirect:/admin/manage-users";
    }

    @GetMapping("/approve-roles")
    public String showApproveRolesPage(Model model) {
        List<User> unapprovedUsers = userApprovalService.getUnapprovedUsers();
        model.addAttribute("unapprovedUsers", unapprovedUsers);
        return "approve_roles";
    }

    @PostMapping("/approve-roles")
    public String approveUserRoles(
            @RequestParam List<String> userIds,
            @RequestParam(required = false) List<String> roles
    ) throws IOException {
        if (roles != null) {
            List<UserRole> userRoles = roles.stream().map(UserRole::valueOf).collect(Collectors.toList());
            userApprovalService.approveUserRoles(userIds, userRoles);
        }
        return "redirect:/admin/approve-roles";
    }
}

