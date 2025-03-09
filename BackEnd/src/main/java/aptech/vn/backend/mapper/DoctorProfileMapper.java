package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.DoctorProfileDTO;
import aptech.vn.backend.entity.DoctorProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorProfileMapper {
    DoctorProfileDTO toDto(DoctorProfile entity);
    DoctorProfile toEntity(DoctorProfileDTO dto);
}