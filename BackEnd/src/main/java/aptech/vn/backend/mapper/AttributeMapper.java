package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.AttributeDTO;
import aptech.vn.backend.entity.Attribute;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AttributeMapper {
    AttributeDTO toDto(Attribute entity);
    Attribute toEntity(AttributeDTO dto);
}