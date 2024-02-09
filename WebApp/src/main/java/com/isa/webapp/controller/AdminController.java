package com.isa.webapp.controller;

import com.isa.webapp.model.User;
import com.isa.webapp.model.UserRole;
import com.isa.webapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/manage-users")
    public String showManageUsersPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("isAdmin", userService.isAdmin(userDetails));
        return "manage_users";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/manage-users";
    }

    @GetMapping("/edit-user/{userId}")
    public String showEditUserPage(@PathVariable Long userId, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserById(userId);
        model.addAttribute("isAdmin", userService.isAdmin(userDetails));
        model.addAttribute("user", user);
        return "edit_user";
    }

    @PostMapping("/update-user")
    public String updateUser(@RequestParam("id") Long id, @ModelAttribute("user") User updatedUser) {
        User existingUser = userService.getUserById(id);
        if (existingUser != null) {
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setSchoolName(updatedUser.getSchoolName());
            existingUser.setUserRole(updatedUser.getUserRole());
            userService.updateUser(existingUser);
        }
        return "redirect:/admin/manage-users";
    }

    @GetMapping("/approve-roles")
    public String showApproveRolesPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<User> unapprovedUsers = userService.getUnapprovedUsers();
        model.addAttribute("isAdmin", userService.isAdmin(userDetails));
        model.addAttribute("unapprovedUsers", unapprovedUsers);
        return "approve_roles";
    }

    @PostMapping("/approve-roles")
    public String approveUserRoles(
            @RequestParam List<Long> userIds,
            @RequestParam(required = false) List<String> roles
    ) {
        if (roles != null && !roles.isEmpty()) {
            List<UserRole> userRoles = roles.stream().map(UserRole::valueOf).toList();
            userIds.forEach(userId -> userService.approveUserRoles(userId, userRoles));
        }
        return "redirect:/admin/approve-roles";
    }

    @GetMapping("/edit-profile")
    public String showEditProfile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        model.addAttribute("isAdmin", userService.isAdmin(userDetails));
        model.addAttribute("user", user);
        return "edit-profile";
    }

    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute User user, @RequestParam("newPassword") String newPassword, @AuthenticationPrincipal UserDetails userDetails) {
        User existingUser = userService.getUserByEmail(userDetails.getUsername());

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        if (!newPassword.isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(newPassword));
        }

        userService.updateUser(existingUser);
        return "redirect:/";
    }
}

