package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.DoctorProfileDTO;
import aptech.vn.backend.entity.DoctorProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorProfileMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    DoctorProfileDTO.GetDoctorProfileDto toGetDoctorProfileDto(DoctorProfile entity);

    DoctorProfile toDoctorProfileEntity(DoctorProfileDTO.SaveDoctorProfileDto dto);
}