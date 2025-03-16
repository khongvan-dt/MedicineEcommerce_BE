package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.ServiceBookingDTO;
import aptech.vn.backend.entity.ServiceBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceBookingMapper {
    @Mapping(target = "serviceId", source = "service.id")
    @Mapping(target = "patientId", source = "patient.id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    ServiceBookingDTO.GetServiceBookingDto toGetServiceBookingDto(ServiceBooking entity);

    ServiceBooking toServiceBookingEntity(ServiceBookingDTO.SaveServiceBookingDto dto);
}