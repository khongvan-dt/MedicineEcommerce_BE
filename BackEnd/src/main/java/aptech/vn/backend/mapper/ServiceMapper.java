package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.ServiceDTO;
import aptech.vn.backend.entity.Service;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceMapper {
    ServiceDTO toDto(Service entity);
    Service toEntity(ServiceDTO dto);
}
