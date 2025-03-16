package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.DiscountDTO;
import aptech.vn.backend.entity.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiscountMapper {
    @Mapping(target = "medicineId", source = "medicine.id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    DiscountDTO.GetDiscountDto toGetDiscountDto(Discount entity);

    Discount toEntity(DiscountDTO.SaveDiscountDto dto);
}