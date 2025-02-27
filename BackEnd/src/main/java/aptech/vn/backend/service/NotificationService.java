package aptech.vn.backend.service;

import aptech.vn.backend.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    Notification save(Notification notification);
    Optional<Notification> findById(Long id);
    List<Notification> findAll();
    Page<Notification> findAll(Pageable pageable);
    void deleteById(Long id);
    List<Notification> findByUserId(Long userId);
    Page<Notification> findByUserId(Long userId, Pageable pageable);
    void sendNotification(Long userId, String message);
    void markAsRead(Long notificationId);
}