package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.RoleDTO;
import aptech.vn.backend.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {
    RoleDTO toDto(Role entity);
    Role toEntity(RoleDTO dto);
}
