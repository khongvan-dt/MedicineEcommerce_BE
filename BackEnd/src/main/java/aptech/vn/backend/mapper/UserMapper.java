package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.UserDTO;
import aptech.vn.backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDTO toDto(User entity);
    User toEntity(UserDTO dto);
}
