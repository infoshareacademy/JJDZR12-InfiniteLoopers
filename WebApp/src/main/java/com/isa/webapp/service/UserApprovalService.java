package com.isa.webapp.service;

import com.isa.webapp.model.User;
import com.isa.webapp.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserApprovalService {

    private final UserManager userManager;

    @Autowired
    public UserApprovalService(UserManager userManager) {
        this.userManager = userManager;
    }

    public void approveUserRoles(List<String> userIds, List<UserRole> roles) throws IOException {
        for (String userId : userIds) {
            User user = userManager.getUserById(userId);
            user.getApprovedRoles().addAll(roles);
            user.setApproved(true);
            userManager.updateUser(user);
        }
    }

    public List<User> getUnapprovedUsers() {
        return userManager.getAllUsers().stream()
                .filter(user -> !user.isApproved())
                .collect(Collectors.toList());
    }
}