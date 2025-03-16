package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.UserDTO;
import aptech.vn.backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    UserDTO.GetUserDto toGetUserDto(User entity);

    User totoGetUserDtoEntity(UserDTO.SaveUserDto dto);
}