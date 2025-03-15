package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.AttributeDTO;
import aptech.vn.backend.entity.Attribute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AttributeMapper {
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    AttributeDTO.GetDto toGetDto(Attribute entity);

    Attribute toEntity(AttributeDTO.SaveDto dto);
}