package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.MedicineDTO;
import aptech.vn.backend.entity.Medicine;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicineMapper {
    MedicineDTO.GetDto toGetDto(Medicine medicine);
    MedicineDTO.InsertDto toInsertDto(Medicine medicine);
    Medicine toEntity(MedicineDTO.InsertDto dto);
}
