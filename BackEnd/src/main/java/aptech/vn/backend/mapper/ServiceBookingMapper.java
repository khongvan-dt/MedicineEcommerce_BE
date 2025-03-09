package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.ServiceBookingDTO;
import aptech.vn.backend.entity.ServiceBooking;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceBookingMapper {
    ServiceBookingDTO toDto(ServiceBooking entity);
    ServiceBooking toEntity(ServiceBookingDTO dto);
}