//package aptech.vn.backend.controller;
//
//import aptech.vn.backend.dto.UserDTO;
//import aptech.vn.backend.service.UserService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeParseException;
//import java.util.Collections;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/users")
//@CrossOrigin("*")
//public class UserController {
//
//    private final UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<UserDTO.GetDto>> getAllUsers() {
//        List<UserDTO.GetDto> users = userService.findAll();
//        return ResponseEntity.ok(users);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserDTO.GetDto> getUserById(@PathVariable Long id) {
//        return userService.findById(id)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping("/save")
//    public ResponseEntity<UserDTO.GetDto> saveOrUpdateUser(@RequestBody UserDTO.SaveDto userDTO) {
//        UserDTO.GetDto savedUser = userService.saveOrUpdate(userDTO);
//        return ResponseEntity.ok(savedUser);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        if (!userService.findById(id).isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        userService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/by-email")
//    public ResponseEntity<UserDTO.GetDto> getUserByEmail(@RequestParam String email) {
//        return userService.findByEmail(email)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<List<UserDTO.GetDto>> searchUsers(
//            @RequestParam(required = false) String fullName,
//            @RequestParam(required = false) String phone,
//            @RequestParam(required = false) String address) {
//        if (fullName != null) {
//            return ResponseEntity.ok(userService.findByFullNameContaining(fullName));
//        } else if (phone != null) {
//            return ResponseEntity.ok(userService.findByPhone(phone));
//        } else if (address != null) {
//            return ResponseEntity.ok(userService.findByAddressContaining(address));
//        }
//        return ResponseEntity.badRequest().build();
//    }
//
//    @GetMapping("/by-enabled")
//    public ResponseEntity<List<UserDTO.GetDto>> getUsersByEnabled(@RequestParam Boolean enabled) {
//        return ResponseEntity.ok(userService.findByEnabled(enabled));
//    }
//
//    @GetMapping("/by-locked")
//    public ResponseEntity<List<UserDTO.GetDto>> getUsersByLocked(@RequestParam Boolean locked) {
//        return ResponseEntity.ok(userService.findByLocked(locked));
//    }
//
//    @GetMapping("/by-last-login")
//    public ResponseEntity<List<UserDTO.GetDto>> getUsersByLastLoginAfter(@RequestParam String date) {
//        try {
//             LocalDateTime dateTime = LocalDateTime.parse(date);
//
//             List<UserDTO.GetDto> users = userService.findByLastLoginAfter(dateTime);
//
//            return ResponseEntity.ok(users);
//        } catch (DateTimeParseException e) {
//             return ResponseEntity.badRequest().body(Collections.emptyList());
//        }
//    }
//}