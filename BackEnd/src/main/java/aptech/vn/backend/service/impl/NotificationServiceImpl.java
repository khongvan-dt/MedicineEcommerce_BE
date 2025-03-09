package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.NotificationDTO;
import aptech.vn.backend.entity.Notification;
import aptech.vn.backend.mapper.NotificationMapper;
import aptech.vn.backend.repository.NotificationRepository;
import aptech.vn.backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    public List<NotificationDTO> findAll() {
        return notificationRepository.findAll().stream()
                .map(notificationMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<NotificationDTO> findById(Long id) {
        return notificationRepository.findById(id)
                .map(notificationMapper::toDto);
    }

    public NotificationDTO save(NotificationDTO notificationDTO) {
        Notification notification = notificationMapper.toEntity(notificationDTO);
        Notification savedNotification = notificationRepository.save(notification);
        return notificationMapper.toDto(savedNotification);
    }

    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }

    public List<NotificationDTO> findByUserId(Long userId) {
        return notificationRepository.findByUser_Id(userId).stream()
                .map(notificationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDTO> findByUserIdAndCreatedAfter(Long userId, LocalDateTime date) {
        return notificationRepository.findByUser_IdAndCreatedAtAfter(userId, date).stream()
                .map(notificationMapper::toDto)
                .collect(Collectors.toList());
    }
}