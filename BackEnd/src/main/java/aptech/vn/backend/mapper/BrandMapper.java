package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.BrandDTO;
import aptech.vn.backend.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandMapper {
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    BrandDTO.GetDto toGetDto(Brand entity);
    Brand toEntity(BrandDTO.SaveDto dto);
}
