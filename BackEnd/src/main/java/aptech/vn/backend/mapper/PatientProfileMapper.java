package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.PatientProfileDTO;
import aptech.vn.backend.entity.PatientProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientProfileMapper {
    PatientProfileDTO toDto(PatientProfile entity);
    PatientProfile toEntity(PatientProfileDTO dto);
}
