package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.ConsultationDTO;
import aptech.vn.backend.entity.Consultation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConsultationMapper {
    @Mapping(target = "doctorId", source = "doctor.id")
    @Mapping(target = "patientId", source = "patient.id")
    ConsultationDTO.GetDto toGetDto(Consultation entity);  // Đảm bảo phương thức này tồn tại

    Consultation toEntity(ConsultationDTO.SaveDto dto);
}