package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.ServiceTypeDTO;
import aptech.vn.backend.entity.ServiceType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceTypeMapper {
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    ServiceTypeDTO.GetDto toGetDto(ServiceType entity);

    ServiceType toEntity(ServiceTypeDTO.SaveDto dto);
}