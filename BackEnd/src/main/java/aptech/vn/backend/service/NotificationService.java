package aptech.vn.backend.service;

import aptech.vn.backend.dto.NotificationDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NotificationService {
    List<NotificationDTO> findAll();
    Optional<NotificationDTO> findById(Long id);
    NotificationDTO save(NotificationDTO notificationDTO);
    void deleteById(Long id);
    List<NotificationDTO> findByUserId(Long userId);
    List<NotificationDTO> findByUserIdAndCreatedAfter(Long userId, LocalDateTime date);
}