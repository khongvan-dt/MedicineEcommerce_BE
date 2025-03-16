package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.PatientProfileDTO;
import aptech.vn.backend.entity.PatientProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientProfileMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    PatientProfileDTO.GetPatientProfileDto toGetPatientProfileDto(PatientProfile entity);

    PatientProfile toPatientProfileEntity(PatientProfileDTO.SavePatientProfileDto dto);
}