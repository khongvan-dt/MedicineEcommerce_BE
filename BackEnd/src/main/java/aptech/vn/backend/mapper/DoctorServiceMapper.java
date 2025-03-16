package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.DoctorServiceDTO;
import aptech.vn.backend.entity.DoctorService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorServiceMapper {

     DoctorServiceDTO.GetDoctorServiceDto toDoctorServiceDto(DoctorService entity);


     DoctorService toDoctorServiceEntity(DoctorServiceDTO.SaveDoctorServiceDto dto);
}