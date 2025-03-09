package aptech.vn.backend.controller;

import aptech.vn.backend.dto.NotificationDTO;
import aptech.vn.backend.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
        List<NotificationDTO> notifications = notificationService.findAll();
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable Long id) {
        Optional<NotificationDTO> notification = notificationService.findById(id);
        return notification.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO notificationDTO) {
        NotificationDTO savedNotification = notificationService.save(notificationDTO);
        return ResponseEntity.ok(savedNotification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationDTO> updateNotification(@PathVariable Long id, @RequestBody NotificationDTO notificationDTO) {
        if (!notificationService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        NotificationDTO updatedNotification = notificationService.save(notificationDTO);
        return ResponseEntity.ok(updatedNotification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        if (!notificationService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        notificationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByUserId(@PathVariable Long userId) {
        List<NotificationDTO> notifications = notificationService.findByUserId(userId);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/by-user-and-date")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByUserAndDate(
            @RequestParam Long userId,
            @RequestParam String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date);
        List<NotificationDTO> notifications = notificationService.findByUserIdAndCreatedAfter(userId, dateTime);
        return ResponseEntity.ok(notifications);
    }
}
