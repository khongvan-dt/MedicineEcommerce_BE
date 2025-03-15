package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.ServiceDTO;
import aptech.vn.backend.entity.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceMapper {
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    ServiceDTO.GetDto toGetDto(Service entity);

    Service toEntity(ServiceDTO.SaveDto dto);
}