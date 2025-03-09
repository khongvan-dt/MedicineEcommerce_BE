package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.DiscountDTO;
import aptech.vn.backend.entity.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiscountMapper {
    DiscountDTO toDto(Discount entity);
    Discount toEntity(DiscountDTO dto);
}
