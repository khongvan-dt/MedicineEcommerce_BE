package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.RoleDTO;
import aptech.vn.backend.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    RoleDTO.GetRoleDto toGetRoleDto(Role entity);

    Role toRoleEntity(RoleDTO.SaveRoleDto dto);
}