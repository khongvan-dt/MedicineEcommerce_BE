package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.DoctorServiceDTO;
import aptech.vn.backend.entity.DoctorService;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorServiceMapper {
    DoctorServiceDTO toDto(DoctorService entity);
    DoctorService toEntity(DoctorServiceDTO dto);
}