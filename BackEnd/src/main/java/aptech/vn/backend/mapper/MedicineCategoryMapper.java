package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.MedicineCategoryDTO;
import aptech.vn.backend.entity.MedicineCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicineCategoryMapper {
    @Mapping(target = "medicineId", source = "medicine.id")
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    MedicineCategoryDTO.GetDto toGetDto(MedicineCategory entity);

    MedicineCategory toEntity(MedicineCategoryDTO.SaveDto dto);
}