package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.NotificationDTO;
import aptech.vn.backend.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    NotificationDTO.GetDto toGetDto(Notification entity);

    Notification toEntity(NotificationDTO.SaveDto dto);
}