package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.MedicineMediaDTO;
import aptech.vn.backend.entity.MedicineMedia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicineMediaMapper {
    @Mapping(target = "medicineId", source = "medicine.id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    MedicineMediaDTO.GetDto toGetDto(MedicineMedia entity);

    MedicineMedia toEntity(MedicineMediaDTO.SaveDto dto);
}