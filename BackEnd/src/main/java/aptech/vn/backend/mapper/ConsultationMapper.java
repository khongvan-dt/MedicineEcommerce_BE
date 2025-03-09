package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.ConsultationDTO;
import aptech.vn.backend.entity.Consultation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConsultationMapper {
    ConsultationDTO toDto(Consultation entity);
    Consultation toEntity(ConsultationDTO dto);
}