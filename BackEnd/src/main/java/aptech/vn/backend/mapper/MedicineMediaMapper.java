package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.MedicineMediaDTO;
import aptech.vn.backend.entity.MedicineMedia;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicineMediaMapper {
    MedicineMediaDTO toDto(MedicineMedia entity);
    MedicineMedia toEntity(MedicineMediaDTO dto);
}