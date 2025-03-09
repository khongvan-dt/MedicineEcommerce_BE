package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.UserRoleDTO;
import aptech.vn.backend.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRoleMapper {
    UserRoleDTO toDto(UserRole entity);
    UserRole toEntity(UserRoleDTO dto);
}
