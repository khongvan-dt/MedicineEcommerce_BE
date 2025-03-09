package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.AppointmentDTO;
import aptech.vn.backend.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppointmentMapper {
    AppointmentDTO toDto(Appointment entity);
    Appointment toEntity(AppointmentDTO dto);
}