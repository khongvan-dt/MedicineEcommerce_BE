package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.SocialAccountDTO;
import aptech.vn.backend.entity.SocialAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SocialAccountMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    SocialAccountDTO.GetDto toGetDto(SocialAccount entity);

    SocialAccount toEntity(SocialAccountDTO.SaveDto dto);
}