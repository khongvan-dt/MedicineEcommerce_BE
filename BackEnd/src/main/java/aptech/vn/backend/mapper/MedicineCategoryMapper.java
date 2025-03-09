package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.MedicineCategoryDTO;
import aptech.vn.backend.entity.MedicineCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicineCategoryMapper {
    MedicineCategoryDTO toDto(MedicineCategory entity);
    MedicineCategory toEntity(MedicineCategoryDTO dto);
}
