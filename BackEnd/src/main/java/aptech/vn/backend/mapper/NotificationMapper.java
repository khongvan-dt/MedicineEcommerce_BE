package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.NotificationDTO;
import aptech.vn.backend.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationMapper {
    NotificationDTO toDto(Notification entity);
    Notification toEntity(NotificationDTO dto);
}
