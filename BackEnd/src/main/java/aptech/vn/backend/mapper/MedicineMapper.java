package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.MedicineDTO;
import aptech.vn.backend.entity.Medicine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicineMapper {
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    MedicineDTO.GetDto toGetDto(Medicine medicine);

    Medicine toEntity(MedicineDTO.SaveDto dto);
}