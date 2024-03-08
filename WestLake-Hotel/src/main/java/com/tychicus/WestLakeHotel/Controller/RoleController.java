package com.tychicus.WestLakeHotel.Controller;

import com.tychicus.WestLakeHotel.Exception.RoleAlreadyExistException;
import com.tychicus.WestLakeHotel.Model.Role;
import com.tychicus.WestLakeHotel.Model.User;
import com.tychicus.WestLakeHotel.Response.RoleResponse;
import com.tychicus.WestLakeHotel.Response.UserResponse;
import com.tychicus.WestLakeHotel.Service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.FOUND;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleService roleService;

    @GetMapping("/all-roles")
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        List<Role> roles = roleService.getRoles();
        List<RoleResponse> roleResponses = new ArrayList<>();
        for (Role role : roles) {
            RoleResponse roleResponse = getRoleResponse(role);
            roleResponses.add(roleResponse);
        }
        return ResponseEntity.ok(roleResponses);
    }

    private RoleResponse getRoleResponse(Role role) {
        return new RoleResponse(role.getId(), role.getName());
    }


    @PostMapping("/create-new-role")
    public ResponseEntity<String> createRole(@RequestBody Role theRole) {
        try {
            roleService.createRole(theRole);
            return ResponseEntity.ok("New role created successfully!");
        } catch (RoleAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{roleId}")
    public void deleteRole(@PathVariable("roleId") Long roleId) {
        roleService.deleteRole(roleId);
    }

    @PostMapping("/remove-all-user-from-role/{roleId}")
    public Role removeAllUsersFromRole(@PathVariable("roleId") Long roleId) {
        return roleService.removeAllUserFromRoleId(roleId);
    }

    @PostMapping("/remove-user-form-role")
    public User removeUserFromRole(@RequestParam("userId") Long userId,@RequestParam("roleId") Long roleId) {
        return roleService.removeUserFromRole(userId, roleId);
    }

    @PostMapping("/assign-user-to-role")
    public User assignUserToRole(@RequestParam("userId") Long userId,@RequestParam("roleId") Long roleId) {
        return roleService.assignRoleToUser(userId, roleId);
    }
}
