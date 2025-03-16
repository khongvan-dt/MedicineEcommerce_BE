package aptech.vn.backend.controller;

import aptech.vn.backend.dto.UserDTO;
import aptech.vn.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
@Tag(name = "User Management", description = "API to manage users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get all users", description = "Returns a list of all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved users")
    })
    @GetMapping
    public ResponseEntity<List<UserDTO.GetUserDto>> getAllUsers() {
        List<UserDTO.GetUserDto> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Get user by ID", description = "Returns a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO.GetUserDto> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Save or update user", description = "Creates a new user or updates an existing one")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User saved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<UserDTO.GetUserDto> saveOrUpdateUser(@RequestBody UserDTO.SaveUserDto userDTO) {
        UserDTO.GetUserDto savedUser = userService.saveOrUpdate(userDTO);
        return ResponseEntity.status(userDTO.getId() == null ? HttpStatus.CREATED : HttpStatus.OK)
                .body(savedUser);
    }

    @Operation(summary = "Save or update user (Alternative endpoint)", description = "Creates a new user or updates an existing one")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User saved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/save")
    public ResponseEntity<UserDTO.GetUserDto> saveOrUpdateUserAlternative(@RequestBody UserDTO.SaveUserDto userDTO) {
        UserDTO.GetUserDto savedUser = userService.saveOrUpdate(userDTO);
        return ResponseEntity.ok(savedUser);
    }

    @Operation(summary = "Delete user", description = "Deletes a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get user by email", description = "Returns a user by email address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/by-email")
    public ResponseEntity<UserDTO.GetUserDto> getUserByEmail(@RequestParam String email) {
        return userService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Search users", description = "Search users by fullName, phone, or address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved users"),
            @ApiResponse(responseCode = "400", description = "Invalid search parameters")
    })
    @GetMapping("/search")
    public ResponseEntity<List<UserDTO.GetUserDto>> searchUsers(
            @RequestParam(required = false) String fullName,
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

    @Operation(summary = "Get users by enabled status", description = "Returns users filtered by enabled status")
    @GetMapping("/by-enabled")
    public ResponseEntity<List<UserDTO.GetUserDto>> getUsersByEnabled(@RequestParam Boolean enabled) {
        return ResponseEntity.ok(userService.findByEnabled(enabled));
    }

    @Operation(summary = "Get users by locked status", description = "Returns users filtered by locked status")
    @GetMapping("/by-locked")
    public ResponseEntity<List<UserDTO.GetUserDto>> getUsersByLocked(@RequestParam Boolean locked) {
        return ResponseEntity.ok(userService.findByLocked(locked));
    }

    @Operation(summary = "Get users by last login date", description = "Returns users who logged in after a specific date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved users"),
            @ApiResponse(responseCode = "400", description = "Invalid date format")
    })
    @GetMapping("/by-last-login")
    public ResponseEntity<List<UserDTO.GetUserDto>> getUsersByLastLoginAfter(@RequestParam String date) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(date);
            List<UserDTO.GetUserDto> users = userService.findByLastLoginAfter(dateTime);
            return ResponseEntity.ok(users);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }
}