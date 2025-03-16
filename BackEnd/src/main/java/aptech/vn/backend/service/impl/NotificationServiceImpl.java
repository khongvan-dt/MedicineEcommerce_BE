package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.NotificationDTO;
import aptech.vn.backend.entity.Notification;
import aptech.vn.backend.entity.User;
import aptech.vn.backend.mapper.NotificationMapper;
import aptech.vn.backend.repository.NotificationRepository;
import aptech.vn.backend.repository.UserRepository;
import aptech.vn.backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final NotificationMapper notificationMapper;

    @Autowired
    public NotificationServiceImpl(
            NotificationRepository notificationRepository,
            UserRepository userRepository,
            NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public List<NotificationDTO.GetNotificationDto> findAll() {
        return notificationRepository.findAll().stream()
                .map(notificationMapper::toGetNotificationDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<NotificationDTO.GetNotificationDto> findById(Long id) {
        return notificationRepository.findById(id)
                .map(notificationMapper::toGetNotificationDto);
    }

    @Override
    @Transactional
    public NotificationDTO.GetNotificationDto saveOrUpdate(NotificationDTO.SaveNotificationDto notificationDTO) {
        Notification notification;

        if (notificationDTO.getId() == null || notificationDTO.getId() == 0) {
            // INSERT case
            notification = new Notification();
            notification.setCreatedAt(LocalDateTime.now());
            notification.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<Notification> existingNotification = notificationRepository.findById(notificationDTO.getId());
            if (existingNotification.isEmpty()) {
                throw new RuntimeException("Notification not found with ID: " + notificationDTO.getId());
            }
            notification = existingNotification.get();
            notification.setUpdatedAt(LocalDateTime.now());
        }

        // Xử lý user relationship
        User user = userRepository.findById(notificationDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + notificationDTO.getUserId()));
        notification.setUser(user);

        // Cập nhật nội dung thông báo
        notification.setMessage(notificationDTO.getMessage());

        Notification savedNotification = notificationRepository.save(notification);
        return notificationMapper.toGetNotificationDto(savedNotification);
    }

    @Override
    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public List<NotificationDTO.GetNotificationDto> findByUserId(Long userId) {
        return notificationRepository.findByUser_Id(userId).stream()
                .map(notificationMapper::toGetNotificationDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDTO.GetNotificationDto> findByUserIdAndCreatedAfter(Long userId, LocalDateTime date) {
        return notificationRepository.findByUser_IdAndCreatedAtAfter(userId, date).stream()
                .map(notificationMapper::toGetNotificationDto)
                .collect(Collectors.toList());
    }
}