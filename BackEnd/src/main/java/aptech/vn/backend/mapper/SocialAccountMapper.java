package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.SocialAccountDTO;
import aptech.vn.backend.entity.SocialAccount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SocialAccountMapper {
    SocialAccountDTO toDto(SocialAccount entity);
    SocialAccount toEntity(SocialAccountDTO dto);
}
