package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.UserRoleDTO;
import aptech.vn.backend.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRoleMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "roleId", source = "role.id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    UserRoleDTO.GetUserRoleDto toGetUserRoleDto(UserRole entity);

    UserRole toUserRoleEntity(UserRoleDTO.SaveUserRoleDto dto);
}