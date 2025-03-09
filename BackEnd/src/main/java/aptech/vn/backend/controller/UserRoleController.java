package aptech.vn.backend.controller;

import aptech.vn.backend.dto.UserRoleDTO;
import aptech.vn.backend.service.UserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public ResponseEntity<List<UserRoleDTO>> getAllUserRoles() {
        List<UserRoleDTO> userRoles = userRoleService.findAll();
        return ResponseEntity.ok(userRoles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleDTO> getUserRoleById(@PathVariable Long id) {
        Optional<UserRoleDTO> userRole = userRoleService.findById(id);
        return userRole.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserRoleDTO> createUserRole(@RequestBody UserRoleDTO userRoleDTO) {
        UserRoleDTO savedUserRole = userRoleService.save(userRoleDTO);
        return ResponseEntity.ok(savedUserRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRoleDTO> updateUserRole(@PathVariable Long id, @RequestBody UserRoleDTO userRoleDTO) {
        if (!userRoleService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        UserRoleDTO updatedUserRole = userRoleService.save(userRoleDTO);
        return ResponseEntity.ok(updatedUserRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserRole(@PathVariable Long id) {
        if (!userRoleService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        userRoleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<UserRoleDTO>> getUserRolesByUserId(@PathVariable Long userId) {
        List<UserRoleDTO> userRoles = userRoleService.findByUserId(userId);
        return ResponseEntity.ok(userRoles);
    }

    @GetMapping("/by-role/{roleId}")
    public ResponseEntity<List<UserRoleDTO>> getUserRolesByRoleId(@PathVariable Long roleId) {
        List<UserRoleDTO> userRoles = userRoleService.findByRoleId(roleId);
        return ResponseEntity.ok(userRoles);
    }

    @GetMapping("/by-user-role")
    public ResponseEntity<UserRoleDTO> getUserRoleByUserIdAndRoleId(@RequestParam Long userId, @RequestParam Long roleId) {
        Optional<UserRoleDTO> userRole = userRoleService.findByUserIdAndRoleId(userId, roleId);
        return userRole.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
