package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.PrescriptionDTO;
import aptech.vn.backend.entity.Prescription;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrescriptionMapper {
    PrescriptionDTO toDto(Prescription entity);
    Prescription toEntity(PrescriptionDTO dto);
}
