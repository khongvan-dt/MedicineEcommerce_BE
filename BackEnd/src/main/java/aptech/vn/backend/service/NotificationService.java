package aptech.vn.backend.service;

import aptech.vn.backend.dto.NotificationDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NotificationService {
    List<NotificationDTO.GetDto> findAll();
    Optional<NotificationDTO.GetDto> findById(Long id);
    NotificationDTO.GetDto saveOrUpdate(NotificationDTO.SaveDto notificationDTO);
    void deleteById(Long id);
    List<NotificationDTO.GetDto> findByUserId(Long userId);
    List<NotificationDTO.GetDto> findByUserIdAndCreatedAfter(Long userId, LocalDateTime date);
}