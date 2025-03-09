package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.BrandDTO;
import aptech.vn.backend.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandMapper {
    BrandDTO toDto(Brand entity);
    Brand toEntity(BrandDTO dto);
}
