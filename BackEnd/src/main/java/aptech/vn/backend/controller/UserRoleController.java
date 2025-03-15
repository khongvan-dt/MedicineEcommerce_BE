package aptech.vn.backend.controller;

import aptech.vn.backend.dto.UserRoleDTO;
import aptech.vn.backend.service.UserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
@CrossOrigin("*")
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public ResponseEntity<List<UserRoleDTO.GetDto>> getAllUserRoles() {
        List<UserRoleDTO.GetDto> userRoles = userRoleService.findAll();
        return ResponseEntity.ok(userRoles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleDTO.GetDto> getUserRoleById(@PathVariable Long id) {
        return userRoleService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<UserRoleDTO.GetDto> saveOrUpdateUserRole(@RequestBody UserRoleDTO.SaveDto userRoleDTO) {
        UserRoleDTO.GetDto savedUserRole = userRoleService.saveOrUpdate(userRoleDTO);
        return ResponseEntity.ok(savedUserRole);
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
    public ResponseEntity<List<UserRoleDTO.GetDto>> getUserRolesByUserId(@PathVariable Long userId) {
        List<UserRoleDTO.GetDto> userRoles = userRoleService.findByUserId(userId);
        return ResponseEntity.ok(userRoles);
    }

    @GetMapping("/by-role/{roleId}")
    public ResponseEntity<List<UserRoleDTO.GetDto>> getUserRolesByRoleId(@PathVariable Long roleId) {
        List<UserRoleDTO.GetDto> userRoles = userRoleService.findByRoleId(roleId);
        return ResponseEntity.ok(userRoles);
    }

    @GetMapping("/by-user-role")
    public ResponseEntity<UserRoleDTO.GetDto> getUserRoleByUserIdAndRoleId(
            @RequestParam Long userId,
            @RequestParam Long roleId) {
        return userRoleService.findByUserIdAndRoleId(userId, roleId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}