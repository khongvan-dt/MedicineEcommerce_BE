package aptech.vn.backend.controller;

import aptech.vn.backend.entity.UserRole;
import aptech.vn.backend.service.UserRoleService;
import aptech.vn.backend.service.impl.UserRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    private final UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleServiceImpl userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public ResponseEntity<List<UserRole>> getAllUserRoles() {
        return ResponseEntity.ok(userRoleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRole> getUserRoleById(@PathVariable Long id) {
        return userRoleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserRole>> getUserRolesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userRoleService.findByUserId(userId));
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<UserRole>> getUserRolesByRoleId(@PathVariable Long roleId) {
        return ResponseEntity.ok(userRoleService.findByRoleId(roleId));
    }

    @GetMapping("/user/{userId}/role-names")
    public ResponseEntity<Set<String>> getRoleNamesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userRoleService.findRoleNamesByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<UserRole> createUserRole(@RequestBody UserRole userRole) {
        return new ResponseEntity<>(userRoleService.save(userRole), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRole> updateUserRole(@PathVariable Long id, @RequestBody UserRole userRole) {
        return userRoleService.findById(id)
                .map(existingUserRole -> {
                    userRole.setId(id);
                    return ResponseEntity.ok(userRoleService.save(userRole));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/assign")
    public ResponseEntity<String> assignRoleToUser(@RequestBody Map<String, Long> request) {
        Long userId = request.get("userId");
        Long roleId = request.get("roleId");

        if (userId == null || roleId == null) {
            return ResponseEntity.badRequest().body("Both userId and roleId are required");
        }

        boolean assigned = userRoleService.assignRoleToUser(userId, roleId);
        if (assigned) {
            return ResponseEntity.ok("Role assigned to user successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to assign role to user");
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeRoleFromUser(@RequestBody Map<String, Long> request) {
        Long userId = request.get("userId");
        Long roleId = request.get("roleId");

        if (userId == null || roleId == null) {
            return ResponseEntity.badRequest().body("Both userId and roleId are required");
        }

        boolean removed = userRoleService.removeRoleFromUser(userId, roleId);
        if (removed) {
            return ResponseEntity.ok("Role removed from user successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to remove role from user");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserRole(@PathVariable Long id) {
        return userRoleService.findById(id)
                .map(userRole -> {
                    userRoleService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}