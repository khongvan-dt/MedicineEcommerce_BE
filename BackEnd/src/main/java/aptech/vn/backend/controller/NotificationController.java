package aptech.vn.backend.controller;

import aptech.vn.backend.dto.NotificationDTO;
import aptech.vn.backend.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<NotificationDTO.GetDto>> getAllNotifications() {
        List<NotificationDTO.GetDto> notifications = notificationService.findAll();
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO.GetDto> getNotificationById(@PathVariable Long id) {
        return notificationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<NotificationDTO.GetDto> saveOrUpdateNotification(@RequestBody NotificationDTO.SaveDto notificationDTO) {
        NotificationDTO.GetDto savedNotification = notificationService.saveOrUpdate(notificationDTO);
        return ResponseEntity.ok(savedNotification);
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
    public ResponseEntity<List<NotificationDTO.GetDto>> getNotificationsByUserId(@PathVariable Long userId) {
        List<NotificationDTO.GetDto> notifications = notificationService.findByUserId(userId);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/by-user-and-date")
    public ResponseEntity<List<NotificationDTO.GetDto>> getNotificationsByUserAndDate(
            @RequestParam Long userId,
            @RequestParam String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date);
        List<NotificationDTO.GetDto> notifications = notificationService.findByUserIdAndCreatedAfter(userId, dateTime);
        return ResponseEntity.ok(notifications);
    }
}