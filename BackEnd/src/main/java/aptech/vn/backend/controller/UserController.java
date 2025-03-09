package aptech.vn.backend.controller;

import aptech.vn.backend.dto.UserDTO;
import aptech.vn.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<UserDTO> user = userService.findById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.save(userDTO);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        if (!userService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        UserDTO updatedUser = userService.save(userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-email")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email) {
        Optional<UserDTO> user = userService.findByEmail(email);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(@RequestParam(required = false) String fullName,
                                                     @RequestParam(required = false) String phone,
                                                     @RequestParam(required = false) String address) {
        if (fullName != null) {
            return ResponseEntity.ok(userService.findByFullNameContaining(fullName));
        } else if (phone != null) {
            return ResponseEntity.ok(userService.findByPhone(phone));
        } else if (address != null) {
            return ResponseEntity.ok(userService.findByAddressContaining(address));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/by-enabled")
    public ResponseEntity<List<UserDTO>> getUsersByEnabled(@RequestParam Boolean enabled) {
        return ResponseEntity.ok(userService.findByEnabled(enabled));
    }

    @GetMapping("/by-locked")
    public ResponseEntity<List<UserDTO>> getUsersByLocked(@RequestParam Boolean locked) {
        return ResponseEntity.ok(userService.findByLocked(locked));
    }

    @GetMapping("/by-last-login")
    public ResponseEntity<List<UserDTO>> getUsersByLastLoginAfter(@RequestParam String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date);
        return ResponseEntity.ok(userService.findByLastLoginAfter(dateTime));
    }

    @GetMapping("/by-lock-count")
    public ResponseEntity<List<UserDTO>> getUsersByLockCount(@RequestParam Integer count) {
        return ResponseEntity.ok(userService.findByCountLockGreaterThanEqual(count));
    }
}
