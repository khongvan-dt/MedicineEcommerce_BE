package aptech.vn.backend.service;

import aptech.vn.backend.dto.NotificationDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NotificationService {
    List<NotificationDTO.GetNotificationDto> findAll();
    Optional<NotificationDTO.GetNotificationDto> findById(Long id);
    NotificationDTO.GetNotificationDto saveOrUpdate(NotificationDTO.SaveNotificationDto notificationDTO);
    void deleteById(Long id);
    List<NotificationDTO.GetNotificationDto> findByUserId(Long userId);
    List<NotificationDTO.GetNotificationDto> findByUserIdAndCreatedAfter(Long userId, LocalDateTime date);
}